package com.oazisn.moviecatalog

import android.app.Application
import android.content.Context
import com.oazisn.moviecatalog.core.di.networkModule
import com.oazisn.moviecatalog.core.di.repoModule
import com.oazisn.moviecatalog.core.di.roomModule
import com.oazisn.moviecatalog.di.appModule
import com.oazisn.moviecatalog.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    roomModule,
                    useCaseModule,
                    appModule,
                    repoModule,
                )
            )
        }
    }
}