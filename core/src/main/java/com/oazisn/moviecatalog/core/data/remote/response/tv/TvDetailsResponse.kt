package com.oazisn.moviecatalog.core.data.remote.response.tv

import com.squareup.moshi.Json

data class TvDetailsResponse(

    @field:Json(name = "original_language")
    val originalLanguage: String? = null,

    @field:Json(name = "number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @field:Json(name = "networks")
    val networks: List<NetworksItem?>? = null,

    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @field:Json(name = "genres")
    val genres: List<GenresItem?>? = null,

    @field:Json(name = "popularity")
    val popularity: Double? = null,

    @field:Json(name = "production_countries")
    val productionCountries: List<ProductionCountriesItem?>? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "number_of_seasons")
    val numberOfSeasons: Int? = null,

    @field:Json(name = "vote_count")
    val voteCount: Int? = null,

    @field:Json(name = "first_air_date")
    val firstAirDate: String? = null,

    @field:Json(name = "overview")
    val overview: String? = null,

    @field:Json(name = "seasons")
    val seasons: List<SeasonsItem?>? = null,

    @field:Json(name = "languages")
    val languages: List<String?>? = null,

    @field:Json(name = "created_by")
    val createdBy: List<CreatedByItem?>? = null,

    @field:Json(name = "last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,

    @field:Json(name = "poster_path")
    val posterPath: String? = null,

    @field:Json(name = "origin_country")
    val originCountry: List<String?>? = null,

    @field:Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>? = null,

    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @field:Json(name = "original_name")
    val originalName: String? = null,

    @field:Json(name = "vote_average")
    val voteAverage: Double? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "tagline")
    val tagline: String? = null,

    @field:Json(name = "episode_run_time")
    val episodeRunTime: List<Int?>? = null,

    @field:Json(name = "next_episode_to_air")
    val nextEpisodeToAir: Any? = null,

    @field:Json(name = "in_production")
    val inProduction: Boolean? = null,

    @field:Json(name = "last_air_date")
    val lastAirDate: String? = null,

    @field:Json(name = "homepage")
    val homepage: String? = null,

    @field:Json(name = "status")
    val status: String? = null
)