package com.oazisn.moviecatalog.core.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.data.local.entity.Movie
import com.oazisn.moviecatalog.core.data.remote.network.MovieApiService
import com.oazisn.moviecatalog.core.domain.model.MoviePagingDomain
import com.oazisn.moviecatalog.core.utils.Utils
import kotlinx.coroutines.flow.Flow

class MovieDataSource(
    private val movieApiService: MovieApiService,
    private val moviesPagingSource: MoviesPagingSource
) {

    suspend fun get(content_id: Int): Movie {
        val details = movieApiService.getDetails(content_id)
        val releaseDates = movieApiService.getReleaseDates(content_id)
        val credits = movieApiService.getCredits(content_id)

        var rated = ""
        releaseDates.results?.filter { it?.iso31661 == "US" }?.forEach { it ->
            it?.releaseDates?.forEach rated@{
                rated = it?.certification ?: "-"
                return@rated
            }
        }

        val genre = details.genres?.joinToString(", ") { it?.name ?: "-" } ?: "-"

        val director = credits.crew?.filter { it?.job == "Director" }?.joinToString(", ") {
            it?.name ?: "-"
        } ?: "-"

        val writers = credits.crew?.filter { it?.department == "Writing" }?.joinToString(", ") {
            it?.name ?: "-"
        } ?: "-"

        val stars = credits.cast?.take(10)?.joinToString(", ") { it?.name ?: "-" } ?: "-"

        return Movie(
            "https://image.tmdb.org/t/p/original${details.posterPath}",
            details.title ?: "-",
            details.voteAverage ?: 0.0,
            details.id ?: 0,
            rated,
            Utils.minToString(details.runtime ?: 0),
            genre,
            details.releaseDate ?: "-",
            details.overview ?: "-",
            director,
            writers,
            stars,
        )
    }

    fun getAll(): Flow<PagingData<MoviePagingDomain>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = { moviesPagingSource }
        ).flow
    }

}