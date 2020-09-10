package com.steve.moviestickearn.di

import com.steve.moviestickearn.core.domain.usecase.MoviesInteractor
import com.steve.moviestickearn.core.domain.usecase.MoviesUseCase
import com.steve.moviestickearn.detail.DetailViewModel
import com.steve.moviestickearn.favorit.FavoritViewModel
import com.steve.moviestickearn.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoritViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}