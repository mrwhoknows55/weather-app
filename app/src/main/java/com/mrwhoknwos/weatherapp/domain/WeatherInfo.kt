package com.mrwhoknwos.weatherapp.domain


data class WeatherInfo(
    val city: CityInfo,
    val weather: Weather,
) {
    data class Weather(
        val title: String,
        val description: String,
        val weatherTime: Long,
        val temperature: Temperature,
        val pressure: Int,
        val humidity: Int,
        val visibility: Int,
        val wind: Wind
    ) {

        data class Temperature(
            val temp: Double,
            val minTemp: Double,
            val maxTemp: Double,
            val feelsLikeTemp: Double,
        )

        data class Wind(
            val speed: Double,
            val degree: Int
        )
    }

    data class CityInfo(
        val cityName: String,
        val timezone: Int,
        val location: Location
    ) {

        data class Location(
            val latitude: Double,
            val longitude: Double
        )
    }
}