package com.oazisn.moviecatalog.favorite.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.oazisn.moviecatalog.core.domain.model.TvShowDomain
import com.oazisn.moviecatalog.core.domain.usecase.TvShowAppUseCase

class TvShowsViewModel(tvShowAppUseCase: TvShowAppUseCase) : ViewModel() {
    val tvShows: LiveData<List<TvShowDomain>> = tvShowAppUseCase.getFavorite().asLiveData()
}