package com.steve.moviestickearn.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.steve.moviestickearn.adapter.MovieAdapter
import com.steve.moviestickearn.adapter.MoviePagerAdapter
import com.steve.moviestickearn.R
import com.steve.moviestickearn.core.data.Resource
import com.steve.moviestickearn.detail.DetailActivity
import kotlinx.android.synthetic.main.layout_home.*

import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {


    private val homeViewModel: HomeViewModel by viewModel()

    companion object {
        var shimmer = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmer = 0
        homeShimmer.visibility = View.VISIBLE
        homeShimmer.startShimmer()
        layoutHome.visibility = View.GONE
        initNowPlaying()
        initTopRated()
        initPopular()

    }

    fun initPopular() {
        val movieAdapter3 = MoviePagerAdapter()
        with(popular_vp) {
            adapter = movieAdapter3
        }
        movieAdapter3.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        homeViewModel.popular.observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                when (movies) {

                    is Resource.Success -> {
                        shimmer++
                        if (shimmer > 2) {
                            homeShimmer.visibility = View.GONE
                            homeShimmer.stopShimmer()
                            layoutHome.visibility = View.VISIBLE
                        }
                        movieAdapter3.setData(movies.data!!)

                    }
                    is Resource.Error -> {
                        Toast.makeText(activity, movies.message, Toast.LENGTH_LONG).show()

                    }
                }
            }
        })
    }


    fun initTopRated() {
        val movieAdapter2 = MovieAdapter()
        with(rv_top_rated) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter2
        }
        movieAdapter2.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
        homeViewModel.top_rated.observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Success -> {
                        shimmer++
                        if (shimmer > 2) {
                            homeShimmer.visibility = View.GONE
                            homeShimmer.stopShimmer()
                            layoutHome.visibility = View.VISIBLE
                        }
                        movieAdapter2.setData(movies.data!!)

                    }
                    is Resource.Error -> {
                        Toast.makeText(activity, movies.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    fun initNowPlaying() {
        homeShimmer.startShimmer()
        val movieAdapter = MovieAdapter()

        with(rv_now_playing) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
        homeViewModel.now_playing.observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                when (movies) {

                    is Resource.Success -> {
                        shimmer++
                        if (shimmer > 2) {
                            homeShimmer.visibility = View.GONE
                            homeShimmer.stopShimmer()
                            layoutHome.visibility = View.VISIBLE
                        }
                        movieAdapter.setData(movies.data!!)

                    }
                    is Resource.Error -> {
                        Toast.makeText(activity, movies.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}