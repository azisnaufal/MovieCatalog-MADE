package com.oazisn.moviecatalog.core.data.local

import com.oazisn.moviecatalog.core.data.local.dao.TvDao
import com.oazisn.moviecatalog.core.data.local.entity.Tv
import kotlinx.coroutines.flow.Flow

class TvShowDataSource(private val tvDao: TvDao) {
    fun getFavorite(): Flow<List<Tv>> = tvDao.getData()

    suspend fun deleteFavorite(tv: Tv) = tvDao.delete(tv)

    suspend fun setFavorite(tv: Tv): Long = tvDao.insert(tv)

    fun isFavoriteExist(id: Int?): Boolean = tvDao.isExist(id)
}