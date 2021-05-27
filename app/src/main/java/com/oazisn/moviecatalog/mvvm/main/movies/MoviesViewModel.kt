package com.oazisn.moviecatalog.mvvm.main.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.oazisn.moviecatalog.core.base.BaseViewModel
import com.oazisn.moviecatalog.core.domain.model.MoviePagingDomain
import com.oazisn.moviecatalog.core.domain.usecase.MovieAppUseCase
import kotlinx.coroutines.flow.Flow

class MoviesViewModel(private val movieAppUseCase: MovieAppUseCase) : BaseViewModel() {

    private var _movies: Flow<PagingData<MoviePagingDomain>>? = null

    override suspend fun loadData() {}

    fun getMovies(): Flow<PagingData<MoviePagingDomain>> {
        val flow = movieAppUseCase.getAll().cachedIn(viewModelScope)
        _movies = flow
        return flow
    }

}