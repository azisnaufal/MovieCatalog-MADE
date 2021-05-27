package com.oazisn.moviecatalog.core.domain.model

import com.oazisn.moviecatalog.core.data.local.entity.Movie

data class MovieDomain(
    val poster: String,
    val title: String,
    val rating: Double,
    val id: Int,
    val rated: String,
    val duration: String, //runtime
    val genre: String,
    val releaseDate: String,
    val desc: String,
    val director: String, // "job": "Director"
    val writers: String, // "department": "Writing"
    val stars: String, // 10 from cast
    val creator: String
) {
    fun toMovie(): Movie =
        Movie(
            poster,
            title,
            rating,
            id,
            rated,
            duration,
            genre,
            releaseDate,
            desc,
            director,
            writers,
            stars,
            creator
        )
}