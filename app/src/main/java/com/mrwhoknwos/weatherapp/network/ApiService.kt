package com.mrwhoknwos.weatherapp.network

import com.mrwhoknwos.weatherapp.util.Constants
import com.mrwhoknwos.weatherapp.util.Secret
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherInfo(
        @Query("q") city: String = "Kolhapur",
        @Query("appid") apiKey: String = Secret.API_KEY
    ): Response<CurrentWeatherResponse>
}

object API {

    private val logging = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}