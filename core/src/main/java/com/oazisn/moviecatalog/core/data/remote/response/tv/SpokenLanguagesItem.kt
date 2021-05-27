package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class SpokenLanguagesItem(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "iso_639_1")
    val iso6391: String? = null,

    @field:Json(name = "english_name")
    val englishName: String? = null
)