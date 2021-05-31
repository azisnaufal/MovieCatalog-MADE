package com.oazisn.moviecatalog.core.data.remote.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oazisn.moviecatalog.core.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    val getApiService: Retrofit by lazy {

        val apiKeyInterceptor = Interceptor { chain ->
            var original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY).build()
            original = original.newBuilder().url(url).build()
            chain.proceed(original)
        }

        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        val mClient = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .certificatePinner(certificatePinner)
            .build()

        return@lazy Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(mClient)
            .build()
    }
}