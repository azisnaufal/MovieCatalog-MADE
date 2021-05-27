package com.oazisn.moviecatalog.core.data.remote.response.movie

import com.squareup.moshi.Json

data class ReleaseDatesItem(

    @field:Json(name = "note")
    val note: String? = null,

    @field:Json(name = "release_date")
    val releaseDate: String? = null,

    @field:Json(name = "type")
    val type: Int? = null,

    @field:Json(name = "iso_639_1")
    val iso6391: String? = null,

    @field:Json(name = "certification")
    val certification: String? = null
)