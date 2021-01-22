package com.mrwhoknwos.weatherapp.ui

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

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d(apiService.getCurrentWeatherInfo().body().toString())
        }
    }
}