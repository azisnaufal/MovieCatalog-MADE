package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class TvShowResultItem(

    @field:Json(name = "first_air_date")
    val firstAirDate: String? = null,

    @field:Json(name = "overview")
    val overview: String? = null,

    @field:Json(name = "original_language")
    val originalLanguage: String? = null,

    @field:Json(name = "genre_ids")
    val genreIds: List<Int?>? = null,

    @field:Json(name = "poster_path")
    val posterPath: String? = null,

    @field:Json(name = "origin_country")
    val originCountry: List<String?>? = null,

    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @field:Json(name = "popularity")
    val popularity: Double? = null,

    @field:Json(name = "vote_average")
    val voteAverage: Double? = null,

    @field:Json(name = "original_name")
    val originalName: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "vote_count")
    val voteCount: Int? = null
)