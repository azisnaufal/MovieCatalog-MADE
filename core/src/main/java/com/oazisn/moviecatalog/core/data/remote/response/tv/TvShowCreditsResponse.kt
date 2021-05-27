package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class TvShowCreditsResponse(

    @field:Json(name = "cast")
    val cast: List<CastItem?>? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "crew")
    val crew: List<CrewItem?>? = null
)