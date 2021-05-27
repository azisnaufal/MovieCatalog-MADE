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
        _movie.postValue(movieAppUseCase.get(contentId))
        _isFavorite.postValue(movieAppUseCase.isFavoriteExist(contentId))
    }

    fun setFav() {
        if (_isFavorite.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                val __movie = _movie.value
                if (__movie != null)
                    movieAppUseCase.deleteFavorite(__movie)

                _isFavorite.postValue(false)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val __movie = _movie.value
                if (__movie != null)
                    movieAppUseCase.setFavorite(__movie)

                _isFavorite.postValue(true)
            }
        }
    }
}