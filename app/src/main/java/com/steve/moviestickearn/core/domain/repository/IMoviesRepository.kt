package com.steve.moviestickearn.core.domain.repository

import com.steve.moviestickearn.core.data.Resource
import com.steve.moviestickearn.core.data.local.entity.MoviesEntity
import com.steve.moviestickearn.core.domain.model.Movie
import com.steve.moviestickearn.core.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getAllMovies(type: Int): Flow<Resource<List<Movie>>>

    fun getFavMovie(): Flow<List<Movie>>

    fun setFavMovie(movie: Movie, state: Boolean)

    fun getReview(id: Int): Flow<Resource<List<Review>>>

    fun getRecommended(id: Int): Flow<Resource<List<Movie>>>
    fun getSimilar(id: Int): Flow<Resource<List<Movie>>>

}