package com.oazisn.moviecatalog.core.domain.usecase

import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.domain.model.MovieDomain
import com.oazisn.moviecatalog.core.domain.model.MoviePagingDomain
import kotlinx.coroutines.flow.Flow

interface MovieAppUseCase {
    suspend fun get(content_id: Int): MovieDomain

    fun getAll(): Flow<PagingData<MoviePagingDomain>>

    fun getFavorite(): Flow<List<MovieDomain>>

    fun deleteFavorite(movie: MovieDomain)

    fun setFavorite(movie: MovieDomain): Long

    fun isFavoriteExist(id: Int?): Boolean
}