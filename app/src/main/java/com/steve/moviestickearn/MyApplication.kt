package com.steve.moviestickearn

import android.app.Application
import com.steve.moviestickearn.core.di.databaseModule
import com.steve.moviestickearn.core.di.networkModule
import com.steve.moviestickearn.core.di.repositoryModule
import com.steve.moviestickearn.di.useCaseModule
import com.steve.moviestickearn.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}