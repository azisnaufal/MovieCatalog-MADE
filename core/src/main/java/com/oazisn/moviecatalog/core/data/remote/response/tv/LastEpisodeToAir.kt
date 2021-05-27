package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class LastEpisodeToAir(

    @field:Json(name = "production_code")
    val productionCode: String? = null,

    @field:Json(name = "air_date")
    val airDate: String? = null,

    @field:Json(name = "overview")
    val overview: String? = null,

    @field:Json(name = "episode_number")
    val episodeNumber: Int? = null,

    @field:Json(name = "vote_average")
    val voteAverage: Double? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "season_number")
    val seasonNumber: Int? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "still_path")
    val stillPath: String? = null,

    @field:Json(name = "vote_count")
    val voteCount: Int? = null
)