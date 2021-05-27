package com.oazisn.moviecatalog.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tb_movie"
)
data class Movie(
    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "rated")
    var rated: String = "",

    @ColumnInfo(name = "duration")
    var duration: String = "", //runtime

    @ColumnInfo(name = "genre")
    var genre: String = "",

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = "",

    @ColumnInfo(name = "desc")
    var desc: String = "",

    @ColumnInfo(name = "director")
    var director: String = "", // "job": "Director"

    @ColumnInfo(name = "writers")
    var writers: String = "", // "department": "Writing"

    @ColumnInfo(name = "stars")
    var stars: String = "", // 10 from cast

    @ColumnInfo(name = "creator")
    var creator: String = ""
)