package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class GenresItem(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "id")
    val id: Int? = null
)