package com.oazisn.moviecatalog.core.domain.repository

import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.domain.model.TvShowDomain
import com.oazisn.moviecatalog.core.domain.model.TvShowPagingDomain
import kotlinx.coroutines.flow.Flow

interface ITvShowAppRepository {
    suspend fun get(content_id: Int): TvShowDomain

    fun getAll(): Flow<PagingData<TvShowPagingDomain>>

    fun getFavorite(): Flow<List<TvShowDomain>>

    fun deleteFavorite(tvShowDomain: TvShowDomain)

    fun setFavorite(tvShowDomain: TvShowDomain): Long

    fun isFavoriteExist(id: Int?): Boolean
}