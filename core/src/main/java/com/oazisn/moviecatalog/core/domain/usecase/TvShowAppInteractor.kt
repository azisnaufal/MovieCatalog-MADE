package com.oazisn.moviecatalog.core.domain.usecase

import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.domain.model.TvShowDomain
import com.oazisn.moviecatalog.core.domain.model.TvShowPagingDomain
import com.oazisn.moviecatalog.core.domain.repository.ITvShowAppRepository
import kotlinx.coroutines.flow.Flow

class TvShowAppInteractor(private val tvApprepository: ITvShowAppRepository) : TvShowAppUseCase {
    override suspend fun get(content_id: Int): TvShowDomain = tvApprepository.get(content_id)

    override fun getAll(): Flow<PagingData<TvShowPagingDomain>> = tvApprepository.getAll()

    override fun getFavorite(): Flow<List<TvShowDomain>> = tvApprepository.getFavorite()

    override fun deleteFavorite(tvShowDomain: TvShowDomain) =
        tvApprepository.deleteFavorite(tvShowDomain)

    override fun setFavorite(tvShowDomain: TvShowDomain): Long =
        tvApprepository.setFavorite(tvShowDomain)

    override fun isFavoriteExist(id: Int?): Boolean = tvApprepository.isFavoriteExist(id)
}