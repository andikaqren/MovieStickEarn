package com.steve.moviestickearn.core.domain.usecase

import android.util.Log
import com.steve.moviestickearn.core.data.Resource
import com.steve.moviestickearn.core.data.local.entity.MoviesEntity
import com.steve.moviestickearn.core.domain.model.Movie
import com.steve.moviestickearn.core.domain.model.Review
import com.steve.moviestickearn.core.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val moviesRepository: IMoviesRepository) : MoviesUseCase {
    override fun getMovies(type: Int): Flow<Resource<List<Movie>>> {
        return moviesRepository.getAllMovies(type)
    }

    override fun getFavMovies(): Flow<List<Movie>> {
        return moviesRepository.getFavMovie()
    }

    override fun setFavMovies(movie: Movie, state: Boolean) {
        moviesRepository.setFavMovie(movie, state)
    }

    override fun getReview(id: Int): Flow<Resource<List<Review>>> {
        val result = moviesRepository.getReview(id)
        return result
    }

    override fun getSimilar(id: Int): Flow<Resource<List<Movie>>> {
        return moviesRepository.getSimilar(id)
    }

    override fun getRecomended(id: Int): Flow<Resource<List<Movie>>> {
        return moviesRepository.getRecommended(id)
    }

}