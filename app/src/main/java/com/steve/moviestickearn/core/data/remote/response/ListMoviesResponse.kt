package com.steve.moviestickearn.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListMoviesResponse(
    @field:SerializedName("page")
    val page: Int,
    @field:SerializedName("results")
    var results: List<MoviesResponse>,
    @field:SerializedName("total_pages")
    val total_pages: Int,
    @field:SerializedName("total_results")
    val total_results: Int

)