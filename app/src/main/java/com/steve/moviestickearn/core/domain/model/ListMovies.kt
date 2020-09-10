package com.steve.moviestickearn.core.domain.model

import com.google.gson.annotations.SerializedName
import com.steve.moviestickearn.core.data.remote.response.MoviesResponse

data class ListMovies(

    val page: Int,

    var results: List<Movie>,

    val total_pages: Int,

    val total_results: Int

)