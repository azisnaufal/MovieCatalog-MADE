package com.oazisn.moviecatalog.core.domain.usecase

import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.domain.model.MovieDomain
import com.oazisn.moviecatalog.core.domain.model.MoviePagingDomain
import com.oazisn.moviecatalog.core.domain.repository.IMovieAppRepository
import kotlinx.coroutines.flow.Flow

class MovieAppInteractor(private val movieAppRepository: IMovieAppRepository) : MovieAppUseCase {
    override suspend fun get(content_id: Int): MovieDomain = movieAppRepository.get(content_id)

    override fun getAll(): Flow<PagingData<MoviePagingDomain>> = movieAppRepository.getAll()

    override fun getFavorite(): Flow<List<MovieDomain>> = movieAppRepository.getFavorite()

    override fun deleteFavorite(movie: MovieDomain) = movieAppRepository.deleteFavorite(movie)

    override fun setFavorite(movie: MovieDomain): Long = movieAppRepository.setFavorite(movie)

    override fun isFavoriteExist(id: Int?): Boolean = movieAppRepository.isFavoriteExist(id)
}