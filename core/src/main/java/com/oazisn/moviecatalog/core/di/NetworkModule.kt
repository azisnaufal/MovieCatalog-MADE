package com.oazisn.moviecatalog.core.di

import com.oazisn.moviecatalog.core.data.remote.MovieDataSource
import com.oazisn.moviecatalog.core.data.remote.MoviesPagingSource
import com.oazisn.moviecatalog.core.data.remote.TvShowDataSource
import com.oazisn.moviecatalog.core.data.remote.TvShowsPagingSource
import com.oazisn.moviecatalog.core.data.remote.network.ApiService
import com.oazisn.moviecatalog.core.data.remote.network.MovieApiService
import com.oazisn.moviecatalog.core.data.remote.network.TvShowApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { provideApiService() }
    single { provideMovieService(get()) }
    single { provideTvShowService(get()) }

    factory { MoviesPagingSource(get()) }
    single { MovieDataSource(get(), get()) }

    factory { TvShowsPagingSource(get()) }
    single { TvShowDataSource(get(), get()) }
}

fun provideApiService(): Retrofit {
    return ApiService.getApiService
}

fun provideMovieService(retrofit: Retrofit): MovieApiService {
    return retrofit.create(MovieApiService::class.java)
}

fun provideTvShowService(retrofit: Retrofit): TvShowApiService {
    return retrofit.create(TvShowApiService::class.java)
}