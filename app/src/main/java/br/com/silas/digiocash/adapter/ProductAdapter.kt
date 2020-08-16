package br.com.silas.digiocash.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.digiocash.R
import br.com.silas.digiocash.model.Products
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class ProductAdapter(private val list: List<Products>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val image: ImageView = itemView.findViewById(R.id.image_produto)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.load_produto)

        fun bind(item: Products) {
            Glide.with(itemView.context)
                .load(item.imageURL)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                })
                .error(R.drawable.ic_baseline_error)
                .into(image)
        }
    }
}