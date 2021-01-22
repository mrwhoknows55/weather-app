package com.mrwhoknwos.weatherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrwhoknwos.weatherapp.domain.WeatherInfo
import com.mrwhoknwos.weatherapp.network.ApiService
import com.mrwhoknwos.weatherapp.util.DtoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel
@ViewModelInject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _currentWeather: MutableLiveData<WeatherInfo> = MutableLiveData()
    val currentWeather: LiveData<WeatherInfo> = _currentWeather

    fun getCurrentWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = apiService.getCurrentWeatherInfo()
                val response = result.body()
                if (result.isSuccessful && response != null) {
                    val weatherInfo = DtoMapper.dtoToDomain(response)
                    _currentWeather.postValue(weatherInfo)
                } else throw Exception("Server error")
            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }

    fun getDailyWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = apiService.getDailyWeatherInfo()
                if (result.isSuccessful)
                    Timber.d(result.body().toString())
            } catch (e: Exception) {
                Timber.d(e)
                e.printStackTrace()
            }
        }
    }
}