package com.mrwhoknwos.weatherapp.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrwhoknwos.weatherapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG", apiService.getCurrentWeatherInfo().body().toString())
        }
    }
}