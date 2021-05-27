package com.oazisn.moviecatalog.core.data.local

import com.oazisn.moviecatalog.core.data.local.dao.MovieDao
import com.oazisn.moviecatalog.core.data.local.entity.Movie
import kotlinx.coroutines.flow.Flow

class MovieDataSource(private val movieDao: MovieDao) {
    fun getFavorite(): Flow<List<Movie>> = movieDao.getData()

    suspend fun deleteFavorite(movie: Movie) = movieDao.delete(movie)

    suspend fun setFavorite(movie: Movie): Long = movieDao.insert(movie)

    fun isFavoriteExist(id: Int?): Boolean = movieDao.isExist(id)
}