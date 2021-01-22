package com.mrwhoknwos.weatherapp.network

import com.mrwhoknwos.weatherapp.util.Secret
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherInfo(
        @Query("q") city: String = "Kolhapur",
        @Query("appid") apiKey: String = Secret.API_KEY
    ): Response<CurrentWeatherResponse>

    @GET("forecast")
    suspend fun getDailyWeatherInfo(
        @Query("q") city: String = "Kolhapur",
        @Query("appid") apiKey: String = Secret.API_KEY
    ): Response<FiveDayForecastResponse>
}