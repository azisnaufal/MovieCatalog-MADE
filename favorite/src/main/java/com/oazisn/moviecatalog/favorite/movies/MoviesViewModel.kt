package com.oazisn.moviecatalog.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.oazisn.moviecatalog.core.domain.model.MovieDomain
import com.oazisn.moviecatalog.core.domain.usecase.MovieAppUseCase

class MoviesViewModel(movieAppUseCase: MovieAppUseCase) : ViewModel() {
    var movies: LiveData<List<MovieDomain>> = movieAppUseCase.getFavorite().asLiveData()
}