package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class TvShowContentRatingsResponse(

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "results")
    val results: List<TvShowContentRatingResultsItem?>? = null
)