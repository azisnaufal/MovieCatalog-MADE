package com.oazisn.moviecatalog.core.di

import androidx.room.Room
import com.oazisn.moviecatalog.core.data.local.AppDatabase
import com.oazisn.moviecatalog.core.data.local.MovieDataSource
import com.oazisn.moviecatalog.core.data.local.TvShowDataSource
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    single { get<AppDatabase>().movieDao() }

    single { get<AppDatabase>().tvDao() }

    single { MovieDataSource(get()) }
    single { TvShowDataSource(get()) }
}