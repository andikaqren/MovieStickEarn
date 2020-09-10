package com.steve.moviestickearn.core.domain.usecase

import com.steve.moviestickearn.core.data.Resource
import com.steve.moviestickearn.core.data.local.entity.MoviesEntity
import com.steve.moviestickearn.core.domain.model.Movie
import com.steve.moviestickearn.core.domain.model.Review

import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getMovies(type: Int): Flow<Resource<List<Movie>>>
    fun getFavMovies(): Flow<List<Movie>>
    fun setFavMovies(movie: Movie, state: Boolean)
    fun getReview(id:Int): Flow<Resource<List<Review>>>
    fun getSimilar(id:Int): Flow<Resource<List<Movie>>>
    fun getRecomended(id:Int): Flow<Resource<List<Movie>>>
}