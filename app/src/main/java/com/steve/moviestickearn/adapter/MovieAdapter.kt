package com.steve.moviestickearn.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.steve.moviestickearn.R
import com.steve.moviestickearn.core.domain.model.Movie
import kotlinx.android.synthetic.main.produk.view.*
import java.util.ArrayList


class MovieAdapter(
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var onItemClick: ((Movie) -> Unit)? = null
    private var listData = ArrayList<Movie>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(result: Movie) {
            with(itemView) {
                val linkPoster =
                    "https://image.tmdb.org/t/p/w500" + result.poster_path
                Glide.with(itemView.context)
                    .load(linkPoster)
                    .placeholder(R.drawable.loading)
                    .into(movies_mini_poster)

                movies_title.text = result.title

            }
        }

        init {
            view.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    fun setData(movies: List<Movie>?) {
        if (movies == null) return
        listData.clear()
        listData.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.produk, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(listData[position])

    }


}