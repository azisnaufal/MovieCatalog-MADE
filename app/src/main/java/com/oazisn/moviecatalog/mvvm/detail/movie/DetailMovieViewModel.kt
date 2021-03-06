package com.oazisn.moviecatalog.mvvm.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.oazisn.moviecatalog.core.base.BaseViewModel
import com.oazisn.moviecatalog.core.domain.model.MovieDomain
import com.oazisn.moviecatalog.core.domain.usecase.MovieAppUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val movieAppUseCase: MovieAppUseCase) : BaseViewModel() {
    private val _movie = MutableLiveData<MovieDomain>()
    val movie: LiveData<MovieDomain> = Transformations.map(_movie) {
        it
    }

    private val _isFavorite = MutableLiveData<Boolean>()
    var isFavorite: LiveData<Boolean> = Transformations.map(_isFavorite) {
        it
    }

    var contentId: Int = 0

    override suspend fun loadData() {
        val result = movieAppUseCase.get(contentId)
        _movie.postValue(result)

        val resultFav = movieAppUseCase.isFavoriteExist(contentId)
        _isFavorite.postValue(resultFav)
    }

    fun setFav() {
        if (_isFavorite.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                val movieDomain = _movie.value
                if (movieDomain != null)
                    movieAppUseCase.deleteFavorite(movieDomain)

                _isFavorite.postValue(false)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val movieDomain = _movie.value
                if (movieDomain != null)
                    movieAppUseCase.setFavorite(movieDomain)

                _isFavorite.postValue(true)
            }
        }
    }
}