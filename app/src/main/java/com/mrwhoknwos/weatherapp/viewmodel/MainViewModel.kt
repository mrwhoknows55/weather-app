package com.mrwhoknwos.weatherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrwhoknwos.weatherapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel
@ViewModelInject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun getCurrentWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d(apiService.getCurrentWeatherInfo().body().toString())
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