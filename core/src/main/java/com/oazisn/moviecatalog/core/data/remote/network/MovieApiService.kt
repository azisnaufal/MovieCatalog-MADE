package com.oazisn.moviecatalog.core.data.remote.network

import com.oazisn.moviecatalog.core.data.remote.response.ListBaseResponse
import com.oazisn.moviecatalog.core.data.remote.response.movie.MovieCreditsResponse
import com.oazisn.moviecatalog.core.data.remote.response.movie.MovieDetailsResponse
import com.oazisn.moviecatalog.core.data.remote.response.movie.MovieReleaseDatesResponse
import com.oazisn.moviecatalog.core.data.remote.response.movie.MovieResultItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int = 1): ListBaseResponse<MovieResultItem>

    @GET("movie/{movie_id}")
    suspend fun getDetails(@Path("movie_id") movie_id: Int): MovieDetailsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") movie_id: Int): MovieCreditsResponse

    @GET("movie/{movie_id}/release_dates")
    suspend fun getReleaseDates(@Path("movie_id") movie_id: Int): MovieReleaseDatesResponse
}