package com.steve.moviestickearn.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.steve.moviestickearn.BottomSheetFragment
import com.steve.moviestickearn.adapter.MovieAdapter
import com.steve.moviestickearn.R
import com.steve.moviestickearn.adapter.ReviewAdapter
import com.steve.moviestickearn.core.data.Resource
import com.steve.moviestickearn.core.domain.model.Movie
import kotlinx.android.synthetic.main.detail_frame.*
import kotlinx.android.synthetic.main.item_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        var shimmer = 0
    }

    private val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        shimmer = 0
        fab_fav.setOnClickListener {
            detailMovie?.let { movie ->
                movie.isFavorit?.let {
                    if (it) {
                        detailMovie.isFavorit = false
                        detailViewModel.setFavoriteMovie(movie, false)
                        setStatusFavorite(false)
                        Snackbar.make(fab_fav, "Success remove from favorite", Snackbar.LENGTH_LONG)
                            .show()
                    } else {
                        detailMovie.isFavorit = true
                        detailViewModel.setFavoriteMovie(movie, true)
                        setStatusFavorite(true)
                        Snackbar.make(fab_fav, "Success add to favorite", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
        fab_share.setOnClickListener {
            detailMovie?.let {
                showBottomSheet(it)
            }
        }
        bindView(detailMovie)

    }

    fun showBottomSheet(movie: Movie) {
        val dialog = BottomSheetFragment()
        dialog.movie = movie
        dialog.show(supportFragmentManager, dialog.tag)


    }


    fun getReview(id: Int) {
        val reviewAdapter = ReviewAdapter()
        with(detail_review_rv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = reviewAdapter
        }
        detailViewModel.findReviews(id).observe(this, Observer {
            if (it != null) {
                when (it) {
                    is Resource.Empty -> {
                        shimmer++
                        detail_review_not_found.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        shimmer++
                        if (shimmer > 2) {
                            detailShimmer.visibility = View.GONE
                            detailShimmer.stopShimmer()
                            detailLayout.visibility = View.VISIBLE
                        }
                        it.data?.let {
                            if (it.size > 0) {
                                detail_review_not_found.visibility = View.INVISIBLE
                                reviewAdapter.setData(it)
                            } else {
                                detail_review_not_found.visibility = View.VISIBLE
                            }
                        }

                    }
                    is Resource.Error -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })


    }

    fun getRecommended(id: Int) {

        val moviesAdapter = MovieAdapter()
        with(detail_recomendation_rv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }
        detailViewModel.findRecomended(id).observe(this, Observer {
            if (it != null) {
                when (it) {
                    is Resource.Empty -> {
                        shimmer++
                        detail_recomendation_text_commingSoon.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {

                        shimmer++
                        if (shimmer > 2) {
                            detailShimmer.visibility = View.GONE
                            detailShimmer.stopShimmer()
                            detailLayout.visibility = View.VISIBLE
                        }
                        it.data?.let {
                            if (it.size > 0) {
                                detail_recomendation_text_commingSoon.visibility = View.INVISIBLE
                                moviesAdapter.setData(it)
                            } else {
                                detail_recomendation_text_commingSoon.visibility = View.VISIBLE
                            }
                        }

                    }
                    is Resource.Error -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })


    }

    fun getSimilar(id: Int) {
        val moviesAdapter = MovieAdapter()
        with(detail_similiar_rv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }
        detailViewModel.findSimilar(id).observe(this, Observer {
            if (it != null) {
                when (it) {
                    is Resource.Empty -> {
                        shimmer++
                        detail_similiar_text_commingSoon.visibility = View.VISIBLE
                    }


                    is Resource.Success -> {
                        shimmer++
                        if (shimmer > 2) {
                            detailShimmer.visibility = View.GONE
                            detailShimmer.stopShimmer()
                            detailLayout.visibility = View.VISIBLE
                        }

                        it.data?.let {
                            if (it.size > 0) {
                                detail_similiar_text_commingSoon.visibility = View.INVISIBLE
                                moviesAdapter.setData(it)
                            } else {
                                detail_similiar_text_commingSoon.visibility = View.VISIBLE
                            }
                        }


                    }
                    is Resource.Error -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()


                    }
                }
            }
        })


    }

    fun bindView(detailMovie: Movie?) {
        detailShimmer.visibility = View.VISIBLE
        detailShimmer.startShimmer()
        detailLayout.visibility = View.GONE
        detailMovie?.let {
            val linkPoster =
                "https://image.tmdb.org/t/p/w500" + it.poster_path
            val linkPoster2 =
                "https://image.tmdb.org/t/p/w500" + it.backdrop_path
            detailTitle.text = it.title
            val rating = it.vote_average!!.toFloat() * 0.5f
            detailRating.rating = rating
            detail_title.text = it.title
            detail_release_date.text = it.release_date
            detail_summary_text.text = it.overview
            Glide.with(this)
                .load(linkPoster)
                .placeholder(R.drawable.loading)
                .into(detailPoster)
            Glide.with(this)
                .load(linkPoster2)
                .placeholder(R.drawable.loading)
                .into(detailCover)
            it.isFavorit?.let {
                setStatusFavorite(it)
            }
            it.id?.let {
                getReview(it)
                getSimilar(it)
                getRecommended(it)

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab_fav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_red))
        } else {
            fab_fav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        }
    }
}