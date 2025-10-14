package com.example.weatherapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.weatherapp.models.Weather
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast

//Aid from AI in setting up inheritance from ViewModel class
open class MainViewModel : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather


    init {
        val current = Current(
            imageId = com.example.weatherapp.R.drawable.snowing,
            condition = "Snowing",
            temperature = "-2°C",
            precipitationType = "Snow",
            precipitationAmount = "5cm",
            wind = "NE 35km/h"
        )

        val dailyForecasts = listOf(
            Forecast(
                date = "Monday, Dec 23",
                imageId = com.example.weatherapp.R.drawable.snowing,
                temperatureHigh = "-2°C",
                temperatureLow = "-10°C",
                condition = "Snowing",
                precipitationType = "Snow",
                precipitationAmount = "5cm",
                precipitationProbability = "80%",
                wind = "NW 15km/h",
                humidity = "85%"
            ),
            Forecast(
                date = "Tuesday, Dec 24",
                imageId = com.example.weatherapp.R.drawable.sunny,
                temperatureHigh = "2°C",
                temperatureLow = "-5°C",
                condition = "Sunny",
                precipitationType = "None",
                precipitationAmount = "0cm",
                precipitationProbability = "10%",
                wind = "W 10km/h",
                humidity = "60%"
            ),
            Forecast(
                date = "Wednesday, Dec 25",
                imageId = com.example.weatherapp.R.drawable.rainy,
                temperatureHigh = "5°C",
                temperatureLow = "1°C",
                condition = "Rainy",
                precipitationType = "Rain",
                precipitationAmount = "10mm",
                precipitationProbability = "90%",
                wind = "S 20km/h",
                humidity = "95%"
            )
        )

    }
}
