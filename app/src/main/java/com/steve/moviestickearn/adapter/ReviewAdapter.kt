package com.steve.moviestickearn.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.steve.moviestickearn.R
import com.steve.moviestickearn.core.domain.model.Review
import kotlinx.android.synthetic.main.review.view.*
import java.util.ArrayList


class ReviewAdapter(
) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    private var listData = ArrayList<Review>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(review: Review) {
            with(itemView) {
                review.author?.let {
                    review_author.text = it
                }
                review.content?.let {
                    review_content.text = it
                }

            }
        }

    }

    fun setData(review: List<Review>?) {
        if (review == null) return
        listData.clear()
        listData.addAll(review)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(listData[position])

    }


}