package com.oazisn.moviecatalog.mvvm.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.oazisn.moviecatalog.core.base.BaseViewModel
import com.oazisn.moviecatalog.core.domain.model.TvShowDomain
import com.oazisn.moviecatalog.core.domain.usecase.TvShowAppUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailTvShowViewModel(private val tvShowAppUseCase: TvShowAppUseCase) : BaseViewModel() {
    private val _tvShow = MutableLiveData<TvShowDomain>()
    val tvShow: LiveData<TvShowDomain> = Transformations.map(_tvShow) {
        it
    }

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = Transformations.map(_isFavorite) {
        it
    }

    var contentId: Int = 0

    override suspend fun loadData() {
        _tvShow.postValue(tvShowAppUseCase.get(contentId))
        _isFavorite.postValue(tvShowAppUseCase.isFavoriteExist(contentId))
    }

    fun setFav() {
        if (_isFavorite.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                val __tvShow = _tvShow.value
                if (__tvShow != null)
                    tvShowAppUseCase.deleteFavorite(__tvShow)

                _isFavorite.postValue(false)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val __tvShow = _tvShow.value
                if (__tvShow != null)
                    tvShowAppUseCase.setFavorite(__tvShow)

                _isFavorite.postValue(true)
            }
        }
    }
}