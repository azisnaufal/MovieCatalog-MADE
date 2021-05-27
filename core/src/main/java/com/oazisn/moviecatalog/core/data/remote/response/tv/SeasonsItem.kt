package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class SeasonsItem(

    @field:Json(name = "air_date")
    val airDate: String? = null,

    @field:Json(name = "overview")
    val overview: String? = null,

    @field:Json(name = "episode_count")
    val episodeCount: Int? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "season_number")
    val seasonNumber: Int? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "poster_path")
    val posterPath: String? = null
)