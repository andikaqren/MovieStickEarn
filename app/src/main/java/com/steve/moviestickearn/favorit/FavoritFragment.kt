package com.steve.moviestickearn.favorit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.steve.moviestickearn.adapter.MovieAdapter
import com.steve.moviestickearn.R
import com.steve.moviestickearn.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorit.*
import org.koin.android.viewmodel.ext.android.viewModel


class FavoritFragment : Fragment() {
    private val favoritViewModel: FavoritViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    fun setupView() {
        val movieAdapter = MovieAdapter()
        with(rv_favorite) {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 2)

        }
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
        favoritViewModel.movies.observe(viewLifecycleOwner, Observer {
          movieAdapter.setData(it)
            favorite_tv_empty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

    }

}