package com.example.weatherapp.ui.weather


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.response.WeatherResponse
import com.example.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository()

    private val _weatherState = MutableLiveData<WeatherResponse?>(null)
    val weatherState: LiveData<WeatherResponse?> get() =  _weatherState


    fun fetchWeather(lat: Double, lon: Double, units: String, appid: String) {
        viewModelScope.launch {
            val result = weatherRepository.getWeather(lat, lon, units, appid)
            result.onSuccess { weatherResponse ->
                _weatherState.postValue(weatherResponse)

            }.onFailure { exception ->
                Log.d("##DEV","onFailure ${exception.message}")
            }
        }
    }
}