package com.oazisn.moviecatalog.core.data

import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.domain.model.MovieDomain
import com.oazisn.moviecatalog.core.domain.model.MoviePagingDomain
import com.oazisn.moviecatalog.core.domain.repository.IMovieAppRepository
import com.oazisn.moviecatalog.core.utils.Utils.toMovieDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import com.oazisn.moviecatalog.core.data.local.MovieDataSource as LocalDataSource
import com.oazisn.moviecatalog.core.data.remote.MovieDataSource as RemoteDataSource

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieAppRepository {

    override suspend fun get(content_id: Int): MovieDomain {
        val result = remoteDataSource.get(content_id)
        return toMovieDomain(result)
    }

    override fun getAll(): Flow<PagingData<MoviePagingDomain>> = remoteDataSource.getAll()

    override fun getFavorite(): Flow<List<MovieDomain>> =
        localDataSource.getFavorite().map { list ->
            list.map {
                toMovieDomain(it)
            }
        }

    override fun deleteFavorite(movie: MovieDomain) {
        return runBlocking {
            localDataSource.deleteFavorite(movie.toMovie())
        }
    }

    override fun setFavorite(movie: MovieDomain): Long {
        return runBlocking {
            localDataSource.setFavorite(movie.toMovie())
        }
    }

    override fun isFavoriteExist(id: Int?): Boolean = localDataSource.isFavoriteExist(id)

}

