package com.oazisn.moviecatalog.core.data.remote.response

import com.squareup.moshi.Json

data class ListBaseResponse<T>(

    @field:Json(name = "page")
    val page: Int? = null,

    @field:Json(name = "total_pages")
    val totalPages: Int? = null,

    @field:Json(name = "results")
    val results: List<T?>? = null,

    @field:Json(name = "total_results")
    val totalResults: Int? = null
)