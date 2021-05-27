package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class TvShowContentRatingResultsItem(

    @field:Json(name = "iso_3166_1")
    val iso31661: String? = null,

    @field:Json(name = "rating")
    val rating: String? = null
)