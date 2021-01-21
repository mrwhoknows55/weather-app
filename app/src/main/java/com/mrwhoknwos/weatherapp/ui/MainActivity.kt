package com.mrwhoknwos.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.mrwhoknwos.weatherapp.R
import com.mrwhoknwos.weatherapp.network.API
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val result = API.service.getCurrentWeatherInfo()

            if (result.isSuccessful)
                Log.d(TAG, result.body().toString())
        }
    }
}