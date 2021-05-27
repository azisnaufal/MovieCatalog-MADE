package com.oazisn.moviecatalog.mvvm.main.tvshows

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oazisn.moviecatalog.core.base.BaseViewModel
import com.oazisn.moviecatalog.core.domain.model.TvShowPagingDomain
import com.oazisn.moviecatalog.core.domain.usecase.TvShowAppUseCase
import kotlinx.coroutines.flow.Flow

class TvShowsViewModel(private val tvShowAppUseCase: TvShowAppUseCase) : BaseViewModel() {

    private var _tvShows: Flow<PagingData<TvShowPagingDomain>>? = null

    override suspend fun loadData() {}

    fun getTvShows(): Flow<PagingData<TvShowPagingDomain>> {
        val flow = tvShowAppUseCase.getAll().cachedIn(viewModelScope)
        _tvShows = flow
        return flow
    }

}