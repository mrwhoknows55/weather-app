package com.mrwhoknwos.weatherapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mrwhoknwos.weatherapp.databinding.ActivityMainBinding
import com.mrwhoknwos.weatherapp.network.ApiService
import com.mrwhoknwos.weatherapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCurrentWeather()

        viewModel.currentWeather.observe(this) {
            binding.tvMainWeatherInfo.text = it.toString()
        }

//        viewModel.getDailyWeather()
    }
}