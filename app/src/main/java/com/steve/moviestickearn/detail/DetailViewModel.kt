package com.steve.moviestickearn.detail

import androidx.lifecycle.*
import com.steve.moviestickearn.core.data.Resource
import com.steve.moviestickearn.core.domain.model.Movie
import com.steve.moviestickearn.core.domain.model.Review
import com.steve.moviestickearn.core.domain.usecase.MoviesUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MoviesUseCase) : ViewModel() {
    val movies = movieUseCase.getMovies(1).asLiveData()

    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavMovies(movie, newStatus)

    fun findReviews(id: Int): LiveData<Resource<List<Review>>> {
        return movieUseCase.getReview(id).asLiveData()
    }

    fun findRecomended(id: Int): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getRecomended(id).asLiveData()

    }

    fun findSimilar(id: Int): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getSimilar(id).asLiveData()
    }
}