package com.steve.moviestickearn.favorit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.steve.moviestickearn.core.domain.usecase.MoviesUseCase

class FavoritViewModel(movieUseCase: MoviesUseCase) : ViewModel() {
    val movies = movieUseCase.getFavMovies().asLiveData()
}