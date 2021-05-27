package com.oazisn.moviecatalog.core.data.remote.response.movie

import com.squareup.moshi.Json

data class ResultsItem(

    @field:Json(name = "release_dates")
    val releaseDates: List<ReleaseDatesItem?>? = null,

    @field:Json(name = "iso_3166_1")
    val iso31661: String? = null
)