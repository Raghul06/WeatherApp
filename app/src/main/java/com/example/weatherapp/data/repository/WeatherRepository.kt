package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.RetrofitClient
import com.example.weatherapp.data.remote.response.WeatherResponse


class WeatherRepository {

    suspend fun getWeather(lat: Double, lon: Double, units: String,appid : String): Result<WeatherResponse> {
        return try {
            val response = RetrofitClient.apiInterFace.getWeather(lat, lon, units, appid)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("No data found"))
            } else {
                Result.failure(Exception("Error code: ${response.code()}, message: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}