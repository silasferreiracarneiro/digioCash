package br.com.silas.digiocash.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.digiocash.BaseApplication
import br.com.silas.digiocash.R
import br.com.silas.digiocash.adapter.ProductAdapter
import br.com.silas.digiocash.adapter.SpotlightAdapter
import br.com.silas.digiocash.model.Cash
import br.com.silas.digiocash.model.HomeRequest
import br.com.silas.digiocash.model.Products
import br.com.silas.digiocash.model.Spotlight
import br.com.silas.digiocash.utils.getColoredString
import br.com.silas.digiocash.viewmodel.HomeViewModel
import br.com.silas.digiocash.viewmodel.events.HomeViewModelEvent
import br.com.silas.digiocash.viewmodel.events.HomeViewModelState
import com.bumptech.glide.Glide
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: HomeViewModel

    private lateinit var recyclerOferta: RecyclerView
    private lateinit var recyclerProduto: RecyclerView
    private lateinit var tituloDigioCash: TextView
    private lateinit var imageDigioCash: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as BaseApplication).getAppComponent()?.inject(this)

        configureStatusBar()
        bindProperties()
        bindViewModel()
        bindObservable()
        loadFiles()
    }

    private fun configureStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun bindProperties() {
        this.recyclerOferta = findViewById(R.id.recycler_ofertas)
        this.recyclerProduto = findViewById(R.id.recycler_produtos)
        this.tituloDigioCash = findViewById(R.id.txt_digio_cash)
        this.imageDigioCash = findViewById(R.id.img_digio_cash)
    }

    private fun bindViewModel() {
        viewModel = of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private fun bindObservable() {
        viewModel.viewEvent.observe(this, Observer {
            when (it) {
                is HomeViewModelEvent.ShowLoading -> null
            }
        })

        viewModel.viewState.observe(this, Observer {
            when (it) {
                is HomeViewModelState.SucessCallApi -> secessCall(it.result)
                is HomeViewModelState.ErrorCallApi -> null
            }
        })
    }

    private fun secessCall(result: HomeRequest?) {
        result?.let {
            setSpotlight(it.spotlight)
            setCash(it.cash)
            setProducts(it.products)
        }
    }

    private fun setProducts(products: List<Products>) {
        setDefaultRecycler(recyclerProduto)
        recyclerProduto.adapter = ProductAdapter(products)
        ((recyclerProduto.adapter as ProductAdapter).notifyDataSetChanged())
    }

    private fun setCash(cash: Cash) {
        Glide
            .with(this)
            .load(cash.bannerURL)
            .into(imageDigioCash)
        configureText(cash.title)
        tituloDigioCash.contentDescription = cash.description
    }

    private fun configureText(title: String) {
        val dividerTitle = title.split(" ")
        tituloDigioCash.append("${dividerTitle.first()} ".getColoredString(ContextCompat.getColor(this ,R.color.azul_020C50)))
        tituloDigioCash.append(dividerTitle.last().getColoredString(ContextCompat.getColor(this ,R.color.cinza_8E8E8F)))
    }

    private fun setSpotlight(spotlight: List<Spotlight>) {
        setDefaultRecycler(recyclerOferta)
        recyclerOferta.adapter = SpotlightAdapter(spotlight)
        ((recyclerOferta.adapter as SpotlightAdapter).notifyDataSetChanged())
    }

    private fun setDefaultRecycler(recycler: RecyclerView) {
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.isNestedScrollingEnabled = false
    }

    private fun loadFiles() {
        viewModel.loadFiles()
    }
}