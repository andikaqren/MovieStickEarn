package com.steve.moviestickearn.core.data.remote.network

import com.steve.moviestickearn.BuildConfig
import com.steve.moviestickearn.core.data.remote.response.KategoriResponse
import com.steve.moviestickearn.core.data.remote.response.ListMoviesResponse
import com.steve.moviestickearn.core.data.remote.response.ReviewResponse
import com.steve.moviestickearn.core.domain.model.ListMovies
import com.steve.moviestickearn.core.domain.model.ListReview
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/top_rated?api_key=${BuildConfig.API}&language=en-US&page=1")
    suspend fun getTopMovies(): ListMoviesResponse

    @GET("movie/{id}/similar?api_key=${BuildConfig.API}&language=en-US&page=1")
    suspend fun getSimilar(@Path("id") id: Int?): ListMovies

    @GET("movie/{id}/recommendations?api_key=${BuildConfig.API}&language=en-US&page=1")
    suspend fun getRecomendation(@Path("id") id: Int?): ListMovies

    @GET("movie/now_playing?api_key=${BuildConfig.API}&language=en-US&page=1")
    suspend fun getNowPlaying(): ListMoviesResponse

    @GET("movie/popular?api_key=${BuildConfig.API}&language=en-US&page=1")
    suspend fun getPopular(): ListMoviesResponse

    @GET("genre/movie/list?api_key=${BuildConfig.API}&language=en-US")
    suspend fun getKategori(): KategoriResponse

    @GET("movie/{id}/reviews?api_key=${BuildConfig.API}&language=en-US")
    suspend fun getReview(@Path("id") id: Int?): ListReview


}
