package com.oazisn.moviecatalog.core.domain.model

data class MoviePagingDomain(
    val poster: String,
    val title: String,
    val rating: Double,
    val id: Int,
)