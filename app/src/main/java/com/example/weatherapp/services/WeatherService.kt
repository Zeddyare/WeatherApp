package com.example.weatherapp.services
import com.example.weatherapp.models.Weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("v1/forecast.json")
    suspend fun getWeather(
        @Query("key") apiKey: String = "097b6aceb9224cfc9d7140007252410",
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"

    ): Weather
}