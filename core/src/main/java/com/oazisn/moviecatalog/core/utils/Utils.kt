package com.oazisn.moviecatalog.core.utils

import com.oazisn.moviecatalog.core.data.local.entity.Movie
import com.oazisn.moviecatalog.core.data.local.entity.Tv
import com.oazisn.moviecatalog.core.domain.model.MovieDomain
import com.oazisn.moviecatalog.core.domain.model.TvShowDomain

object Utils {
    fun minToString(runtime: Int): String {
        val durationHours: Int = runtime / 60
        val durationMinutes: Int = runtime % 60

        var str = ""
        if (durationHours > 0) {
            str += "${durationHours}h "
        }
        str += "${durationMinutes}min"

        return str
    }

    fun toMovieDomain(movie: Movie): MovieDomain =
        MovieDomain(
            movie.poster,
            movie.title,
            movie.rating,
            movie.id,
            movie.rated,
            movie.duration,
            movie.genre,
            movie.releaseDate,
            movie.desc,
            movie.director,
            movie.writers,
            movie.stars,
            movie.creator
        )

    fun toTvShowDomain(tv: Tv): TvShowDomain =
        TvShowDomain(
            tv.poster,
            tv.title,
            tv.rating,
            tv.id,
            tv.rated,
            tv.duration,
            tv.genre,
            tv.releaseDate,
            tv.desc,
            tv.director,
            tv.writers,
            tv.stars,
            tv.creator
        )

}