package com.oazisn.moviecatalog.core.data.remote.network

import com.oazisn.moviecatalog.core.data.remote.response.ListBaseResponse
import com.oazisn.moviecatalog.core.data.remote.response.tv.TvDetailsResponse
import com.oazisn.moviecatalog.core.data.remote.response.tv.TvShowContentRatingsResponse
import com.oazisn.moviecatalog.core.data.remote.response.tv.TvShowCreditsResponse
import com.oazisn.moviecatalog.core.data.remote.response.tv.TvShowResultItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {

    @GET("tv/popular")
    suspend fun getPopular(@Query("page") page: Int = 1): ListBaseResponse<TvShowResultItem>

    @GET("tv/{tv_id}")
    suspend fun getDetails(@Path("tv_id") tv_id: Int): TvDetailsResponse

    @GET("tv/{tv_id}/credits")
    suspend fun getCredits(@Path("tv_id") tv_id: Int): TvShowCreditsResponse

    @GET("tv/{tv_id}/content_ratings")
    suspend fun getContentRatings(@Path("tv_id") tv_id: Int): TvShowContentRatingsResponse
}