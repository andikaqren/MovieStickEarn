package com.steve.moviestickearn.core.data.local

import com.steve.moviestickearn.core.data.local.entity.MoviesEntity
import com.steve.moviestickearn.core.data.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val moviesDao: MoviesDao) {

    fun getAllMovies(): Flow<List<MoviesEntity>> = moviesDao.getAllMovies()

    fun getPopular(): Flow<List<MoviesEntity>> = moviesDao.getPopular()

    fun getNowPlaying(): Flow<List<MoviesEntity>> = moviesDao.getNowPlaying()

    fun getTopRated(): Flow<List<MoviesEntity>> = moviesDao.getTopRated()

    fun getFavMovies(): Flow<List<MoviesEntity>> = moviesDao.getFavMovies()

    suspend fun insertMovies(movieList: List<MoviesEntity>) =
        moviesDao.inserMovies(movieList)

    fun setFavMovies(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorit = newState
        moviesDao.updateFavMovies(movies)
    }
}