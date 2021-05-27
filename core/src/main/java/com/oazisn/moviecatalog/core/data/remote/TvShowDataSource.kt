package com.oazisn.moviecatalog.core.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.oazisn.moviecatalog.core.data.local.entity.Tv
import com.oazisn.moviecatalog.core.data.remote.network.TvShowApiService
import com.oazisn.moviecatalog.core.domain.model.TvShowPagingDomain
import com.oazisn.moviecatalog.core.utils.Utils
import kotlinx.coroutines.flow.Flow

class TvShowDataSource(
    private val tvShowApiService: TvShowApiService,
    private val tvShowsPagingSource: TvShowsPagingSource
) {
    suspend fun get(content_id: Int): Tv {
        val details = tvShowApiService.getDetails(content_id)
        val contentRating = tvShowApiService.getContentRatings(content_id)
        val credits = tvShowApiService.getCredits(content_id)

        var rated = ""
        contentRating.results?.filter { it?.iso31661 == "US" }?.forEach loop@{
            rated = it?.rating ?: "-"
            return@loop
        }

        val genre = details.genres?.joinToString(", ") { it?.name ?: "-" } ?: "-"

        val director = credits.crew?.filter { it?.job == "Director" }?.joinToString(", ") {
            it?.name ?: "-"
        } ?: "-"

        val writers = credits.crew?.filter { it?.department == "Writing" }?.joinToString(", ") {
            it?.name ?: "-"
        } ?: "-"

        val stars = credits.cast?.take(10)?.joinToString(", ") { it?.name ?: "-" } ?: "-"

        return Tv(
            "https://image.tmdb.org/t/p/original${details.posterPath}",
            details.name ?: "-",
            details.voteAverage ?: 0.0,
            details.id ?: 0,
            rated,
            Utils.minToString(details.episodeRunTime?.get(0) ?: 0),
            genre,
            details.firstAirDate ?: "-",
            details.overview ?: "-",
            director,
            writers,
            stars,
        )
    }

    fun getAll(): Flow<PagingData<TvShowPagingDomain>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = { tvShowsPagingSource }
        ).flow
    }
}