package br.com.silas.digiocash.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import br.com.silas.digiocash.R
import br.com.silas.digiocash.model.Spotlight
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class SpotlightAdapter(private val spotlight: List<Spotlight>): RecyclerView.Adapter<SpotlightAdapter.SpotlightViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holofote, parent, false)

        return SpotlightViewHolder(view)
    }

    override fun getItemCount() = spotlight.size

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        val item = spotlight[position]
        holder.bind(item)
    }

    class SpotlightViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.image_spotlight)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.load_spotlight)

        fun bind(item: Spotlight) {
            Glide.with(itemView.context)
                .load(item.bannerURL)
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
                .centerCrop()
                .error(R.drawable.ic_baseline_error)
                .into(image)
        }
    }
}