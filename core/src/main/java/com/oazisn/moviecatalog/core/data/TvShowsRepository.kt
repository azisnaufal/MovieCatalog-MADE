package com.oazisn.moviecatalog.core.data

import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.domain.model.TvShowDomain
import com.oazisn.moviecatalog.core.domain.model.TvShowPagingDomain
import com.oazisn.moviecatalog.core.domain.repository.ITvShowAppRepository
import com.oazisn.moviecatalog.core.utils.Utils.toTvShowDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import com.oazisn.moviecatalog.core.data.local.TvShowDataSource as LocalDataSource
import com.oazisn.moviecatalog.core.data.remote.TvShowDataSource as RemoteDataSource

class TvShowsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ITvShowAppRepository {

    override suspend fun get(content_id: Int): TvShowDomain {
        val result = remoteDataSource.get(content_id)
        return toTvShowDomain(result)
    }

    override fun getAll(): Flow<PagingData<TvShowPagingDomain>> = remoteDataSource.getAll()

    override fun getFavorite(): Flow<List<TvShowDomain>> =
        localDataSource.getFavorite().map { list ->
            list.map {
                toTvShowDomain(it)
            }
        }

    override fun deleteFavorite(tvShowDomain: TvShowDomain) {
        return runBlocking {
            localDataSource.deleteFavorite(tvShowDomain.toTv())
        }
    }

    override fun setFavorite(tvShowDomain: TvShowDomain): Long {
        return runBlocking {
            localDataSource.setFavorite(tvShowDomain.toTv())
        }
    }

    override fun isFavoriteExist(id: Int?): Boolean = localDataSource.isFavoriteExist(id)


}