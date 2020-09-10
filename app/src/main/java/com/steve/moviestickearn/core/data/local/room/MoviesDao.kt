package com.steve.moviestickearn.core.data.local.room

import androidx.room.*
import com.steve.moviestickearn.core.data.local.entity.MoviesEntity
import com.steve.moviestickearn.core.utils.Static
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movie where type = ${Static.TOP_RATED}")
    fun getTopRated(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movie where type = ${Static.NOW_PLAYING}")
    fun getNowPlaying(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movie where type = ${Static.POPULAR}")
    fun getPopular(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movie where isFavorit")
    fun getFavMovies(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserMovies(movies: List<MoviesEntity>)

    @Update
    fun updateFavMovies(movie: MoviesEntity)
}
