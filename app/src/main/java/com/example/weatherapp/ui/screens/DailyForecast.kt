package com.example.weatherapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.models.Weather
import com.example.weatherapp.models.Forecast

//Requires the following:
//a daily forecast for 3 or more days

//Each forecast should include:
//date
//weather image
//temperature high and low for the day
//condition
//precipitation type, amount, and chance
//wind speed and direction
//humidity

@Composable
fun DailyForecast(mainViewModel : MainViewModel) {
    val weather by mainViewModel.weather.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(vertical = 20.dp)
    ) {
        weather?.forecast?.forEach { forecast ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = forecast.imageId),
                    contentDescription = forecast.condition,
                    modifier = Modifier.size(200.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(text = forecast.date)
                    Text(text = "High: ${forecast.temperatureHigh} Low: ${forecast.temperatureLow}")
                    Text(text = "Condition: ${forecast.condition}")
                    Text(text = "Precipitation: ${forecast.precipitationProbability} chance, ${forecast.precipitationAmount} (${forecast.precipitationType})")
                    Text(text = "Wind: ${forecast.wind}")
                    Text(text = "Humidity: ${forecast.humidity}")
                }
            }
        }
    }
}