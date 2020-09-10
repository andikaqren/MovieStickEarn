package com.steve.moviestickearn.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.steve.moviestickearn.R
import com.steve.moviestickearn.core.domain.model.Movie
import kotlinx.android.synthetic.main.promo.view.*
import java.util.ArrayList


class MoviePagerAdapter() :
    PagerAdapter() {
    var onItemClick: ((Movie) -> Unit)? = null
    private var list = ArrayList<Movie>()

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view === `object`
    }

    fun setData(movies: List<Movie>) {

        list.clear()
        list.addAll(movies)
        notifyDataSetChanged()

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view: View = LayoutInflater.from(container.context)
            .inflate(R.layout.promo, container, false)
        with(view) {
            val linkPoster =
                "https://image.tmdb.org/t/p/w500" + list[position].backdrop_path
            if (!list[position].backdrop_path.equals("")) {
                Glide.with(container.context)
                    .load(linkPoster)
                    .placeholder(R.drawable.loading)
                    .into(item_home_promo_iv)
            }
            starText.visibility = View.VISIBLE
            val rating = list[position].vote_average!! * 0.5
            item_home_promo_name.text = list[position].title
            starText.text = rating.toString()
            container.addView(view)
        }
        view.setOnClickListener({
            onItemClick?.invoke(list[position])
        })

        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as CardView)
    }

}