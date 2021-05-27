package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class ProductionCountriesItem(

    @field:Json(name = "iso_3166_1")
    val iso31661: String? = null,

    @field:Json(name = "name")
    val name: String? = null
)