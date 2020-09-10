package com.steve.moviestickearn.core.utils

import com.steve.moviestickearn.core.data.local.entity.MoviesEntity
import com.steve.moviestickearn.core.data.remote.response.MoviesResponse
import com.steve.moviestickearn.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MoviesResponse>, type: Int): List<MoviesEntity> {
        val movies = ArrayList<MoviesEntity>()
        input.map {
            val movie = MoviesEntity(
                adult = it.adult,
                backdrop_path = it.backdrop_path,
                genre_ids = it.genre_ids,
                original_language = it.original_language,
                original_title = it.original_title,
                id = it.id,
                isFavorit = false,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                video = it.video,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                type = type
            )
            movies.add(movie)
        }
        return movies
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movie> =
        input.map {
            Movie(
                adult = it.adult,
                backdrop_path = it.backdrop_path,
                genre_ids = it.genre_ids,
                original_language = it.original_language,
                original_title = it.original_title,
                id = it.id,
                isFavorit = it.isFavorit,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                video = it.video,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                type = it.type
            )
        }

    fun mapDomainToEntity(it: Movie) =
        MoviesEntity(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids,
            original_language = it.original_language,
            original_title = it.original_title,
            id = it.id,
            isFavorit = false,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count,
            type = it.type
        )

}