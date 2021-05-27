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
        val result = tvShowAppUseCase.get(contentId)
        _tvShow.postValue(result)

        val resultFav = tvShowAppUseCase.isFavoriteExist(contentId)
        _isFavorite.postValue(resultFav)
    }

    fun setFav() {
        if (_isFavorite.value == true) {
            viewModelScope.launch(Dispatchers.IO) {
                val tvShowDomain = _tvShow.value
                if (tvShowDomain != null)
                    tvShowAppUseCase.deleteFavorite(tvShowDomain)

                _isFavorite.postValue(false)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val tvShowDomain = _tvShow.value
                if (tvShowDomain != null)
                    tvShowAppUseCase.setFavorite(tvShowDomain)

                _isFavorite.postValue(true)
            }
        }
    }
}