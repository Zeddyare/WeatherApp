package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.weatherapp.models.Weather
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import com.example.weatherapp.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.weatherapp.services.WeatherService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //retrofit
    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val weatherService = retrofit.create(WeatherService::class.java)
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather


    init {
        //fetch weather from API
        fetchWeather("Lunenburg", 3)
    }

    fun fetchWeather(location: String, days: Int) {
        //use coroutine to fetch weather
        viewModelScope.launch {
            val fetchedWeather = weatherService.getWeather(location = location, days = days)
            _weather.value = fetchedWeather
        }
    }





    //init {
//        _weather.value = Weather(
//            current = Current(
//                imageId = R.drawable.sunny,
//                condition = "Sunny",
//                temperature = "18°C",
//                precipitationType = "None",
//                precipitationAmount = "0mm",
//                wind = "NW 10 km/h"
//            ),
//            forecast = listOf(
//                Forecast(
//                    date = "Thursday Oct. 16",
//                    imageId = R.drawable.rainy,
//                    temperatureHigh = "10°C",
//                    temperatureLow = "-1°C",
//                    condition = "Raining",
//                    precipitationType = "Rain",
//                    precipitationAmount = "2mm",
//                    precipitationProbability = "30%",
//                    wind = "W 8 km/h",
//                    humidity = "65%"
//                ),
//                Forecast(
//                    date = "Friday Oct. 17",
//                    imageId = R.drawable.snowing,
//                    temperatureHigh = "2°C",
//                    temperatureLow = "-10°C",
//                    condition = "Snowing",
//                    precipitationType = "Snow",
//                    precipitationAmount = "8cm",
//                    precipitationProbability = "40%",
//                    wind = "SW 12 km/h",
//                    humidity = "70%"
//                ),
//                Forecast(
//                    date = "Saturday Oct. 18",
//                    imageId = R.drawable.rainy,
//                    temperatureHigh = "8°C",
//                    temperatureLow = "0°C",
//                    condition = "Raining",
//                    precipitationType = "Rain",
//                    precipitationAmount = "5mm",
//                    precipitationProbability = "80%",
//                    wind = "E 15 km/h",
//                    humidity = "85%"
//                )
//            )
//        )
    }

