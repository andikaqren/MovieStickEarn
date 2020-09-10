package com.steve.moviestickearn.core.data

import android.util.Log
import com.steve.moviestickearn.core.data.local.LocalDataSource
import com.steve.moviestickearn.core.data.remote.RemoteDataSource
import com.steve.moviestickearn.core.data.remote.network.ApiResponse
import com.steve.moviestickearn.core.data.remote.response.MoviesResponse
import com.steve.moviestickearn.core.domain.model.Movie
import com.steve.moviestickearn.core.domain.model.Review
import com.steve.moviestickearn.core.domain.repository.IMoviesRepository
import com.steve.moviestickearn.core.utils.AppExecutors
import com.steve.moviestickearn.core.utils.DataMapper
import com.steve.moviestickearn.core.utils.Static
import kotlinx.coroutines.flow.*

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {
    override fun getAllMovies(type: Int): Flow<Resource<List<Movie>>> =
        object : NetworkBoundSource<List<Movie>, List<MoviesResponse>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                if (type == Static.NOW_PLAYING) {
                    return localDataSource.getNowPlaying().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
                } else if (type == Static.TOP_RATED) {
                    return localDataSource.getTopRated().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
                } else if (type == Static.POPULAR) {
                    return localDataSource.getPopular().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
                }
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> {

                if (type == Static.NOW_PLAYING) {
                    return remoteDataSource.getNowPlaying()
                } else if (type == Static.TOP_RATED) {
                    return remoteDataSource.getTopMovies()
                } else if (type == Static.POPULAR) {
                    return remoteDataSource.getPopular()
                }
                return remoteDataSource.getNowPlaying()
            }


            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val movies = DataMapper.mapResponsesToEntities(data, type)
                localDataSource.insertMovies(movies)
            }

        }.asFlow()


    override fun getSimilar(id: Int): Flow<Resource<List<Movie>>> =
        object : NetworkBoundSource2<List<Movie>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<Movie>>> {
                return remoteDataSource.getSimilar(id)
            }
        }.asFlow()


    override fun getFavMovie(): Flow<List<Movie>> {
        return localDataSource.getFavMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    }

    override fun setFavMovie(movie: Movie, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavMovies(moviesEntity, state)
        }


    }

    override fun getReview(id: Int): Flow<Resource<List<Review>>> =

        object : NetworkBoundSource2<List<Review>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<Review>>> {
                return remoteDataSource.getReview(id)
            }
        }.asFlow()

    override fun getRecommended(id: Int): Flow<Resource<List<Movie>>> =

        object : NetworkBoundSource2<List<Movie>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<Movie>>> {
                return remoteDataSource.getRecommended(id)
            }
        }.asFlow()


}



