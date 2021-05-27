package com.oazisn.moviecatalog.di

import com.oazisn.moviecatalog.core.domain.usecase.MovieAppInteractor
import com.oazisn.moviecatalog.core.domain.usecase.MovieAppUseCase
import com.oazisn.moviecatalog.core.domain.usecase.TvShowAppInteractor
import com.oazisn.moviecatalog.core.domain.usecase.TvShowAppUseCase
import com.oazisn.moviecatalog.mvvm.detail.movie.DetailMovieViewModel
import com.oazisn.moviecatalog.mvvm.detail.tvshow.DetailTvShowViewModel
import com.oazisn.moviecatalog.mvvm.main.movies.MoviesViewModel
import com.oazisn.moviecatalog.mvvm.main.tvshows.TvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieAppUseCase> { MovieAppInteractor(get()) }
    factory<TvShowAppUseCase> { TvShowAppInteractor(get()) }
}

val appModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}
