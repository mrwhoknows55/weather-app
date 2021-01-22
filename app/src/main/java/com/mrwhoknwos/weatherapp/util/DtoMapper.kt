package com.mrwhoknwos.weatherapp.util

import com.mrwhoknwos.weatherapp.domain.WeatherInfo
import com.mrwhoknwos.weatherapp.network.CurrentWeatherResponse

object DtoMapper {

    fun dtoToDomain(currentWeatherDto: CurrentWeatherResponse): WeatherInfo {

        val city = WeatherInfo.CityInfo(
            cityName = currentWeatherDto.name,
            timezone = currentWeatherDto.timezone,
            WeatherInfo.CityInfo.Location(
                currentWeatherDto.coord.lat,
                currentWeatherDto.coord.lon
            )
        )

        val temperature = WeatherInfo.Weather.Temperature(
            temp = currentWeatherDto.main.temp,
            maxTemp = currentWeatherDto.main.tempMax,
            minTemp = currentWeatherDto.main.tempMin,
            feelsLikeTemp = currentWeatherDto.main.feelsLike,
        )

        val weather = WeatherInfo.Weather(
            title = currentWeatherDto.weather[0].main,
            description = currentWeatherDto.weather[0].description,
            weatherTime = currentWeatherDto.dt,
            temperature = temperature,
            pressure = currentWeatherDto.main.pressure,
            humidity = currentWeatherDto.main.humidity,
            visibility = currentWeatherDto.visibility,
            wind = WeatherInfo.Weather.Wind(
                speed = currentWeatherDto.wind.speed,
                degree = currentWeatherDto.wind.deg
            )
        )


        return WeatherInfo(
            city = city,
            weather = weather
        )
    }
}