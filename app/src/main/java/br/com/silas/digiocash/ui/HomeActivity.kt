package br.com.silas.digiocash.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import br.com.silas.digiocash.BaseApplication
import br.com.silas.digiocash.R
import br.com.silas.digiocash.viewmodel.HomeViewModel
import br.com.silas.digiocash.viewmodel.events.HomeViewModelEvent
import br.com.silas.digiocash.viewmodel.events.HomeViewModelState
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as BaseApplication).getAppComponent()?.inject(this)

        bindViewModel()
        bindObservable()
        loadFiles()
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
                is HomeViewModelState.SucessCallApi -> null
                is HomeViewModelState.ErrorCallApi -> null
            }
        })
    }

    private fun loadFiles() {
        viewModel.loadFiles()
    }
}