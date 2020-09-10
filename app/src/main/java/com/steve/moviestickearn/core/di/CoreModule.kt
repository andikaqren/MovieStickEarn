package com.steve.moviestickearn.core.di

import androidx.room.Room
import com.steve.moviestickearn.BuildConfig
import com.steve.moviestickearn.core.data.MovieRepository
import com.steve.moviestickearn.core.data.local.LocalDataSource
import com.steve.moviestickearn.core.data.local.room.MoviesDatabase
import com.steve.moviestickearn.core.data.remote.RemoteDataSource
import com.steve.moviestickearn.core.data.remote.network.ApiService
import com.steve.moviestickearn.core.domain.repository.IMoviesRepository
import com.steve.moviestickearn.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().moviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "movies.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("${BuildConfig.URL}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMoviesRepository> { MovieRepository(get(), get(), get()) }
}