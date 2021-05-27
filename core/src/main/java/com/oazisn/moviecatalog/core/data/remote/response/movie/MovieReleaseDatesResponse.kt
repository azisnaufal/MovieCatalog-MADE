package com.oazisn.moviecatalog.core.data.remote.response.movie

import com.squareup.moshi.Json

data class MovieReleaseDatesResponse(

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "results")
    val results: List<ResultsItem?>? = null
)