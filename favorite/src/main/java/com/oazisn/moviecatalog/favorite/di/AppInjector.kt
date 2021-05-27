package com.oazisn.moviecatalog.favorite.di

import com.oazisn.moviecatalog.favorite.movies.MoviesViewModel
import com.oazisn.moviecatalog.favorite.tvshows.TvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
}
