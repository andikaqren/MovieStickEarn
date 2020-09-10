package com.steve.moviestickearn.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.steve.moviestickearn.core.domain.usecase.MoviesUseCase
import com.steve.moviestickearn.core.utils.Static

class HomeViewModel(movieUseCase: MoviesUseCase) : ViewModel() {
    val popular = movieUseCase.getMovies(Static.POPULAR).asLiveData()
    val top_rated = movieUseCase.getMovies(Static.TOP_RATED).asLiveData()
    val now_playing = movieUseCase.getMovies(Static.NOW_PLAYING).asLiveData()
}
