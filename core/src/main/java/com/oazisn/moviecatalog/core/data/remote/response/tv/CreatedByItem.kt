package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class CreatedByItem(

    @field:Json(name = "gender")
    val gender: Int? = null,

    @field:Json(name = "credit_id")
    val creditId: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "profile_path")
    val profilePath: String? = null,

    @field:Json(name = "id")
    val id: Int? = null
)