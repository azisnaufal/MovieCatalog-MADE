package com.oazisn.moviecatalog.core.di

import com.oazisn.moviecatalog.core.data.MoviesRepository
import com.oazisn.moviecatalog.core.data.TvShowsRepository
import com.oazisn.moviecatalog.core.domain.repository.IMovieAppRepository
import com.oazisn.moviecatalog.core.domain.repository.ITvShowAppRepository
import org.koin.dsl.module

val repoModule = module {
    single<ITvShowAppRepository> { TvShowsRepository(get(), get()) }
    single<IMovieAppRepository> { MoviesRepository(get(), get()) }

}