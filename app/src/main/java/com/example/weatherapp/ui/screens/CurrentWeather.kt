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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding

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
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R

//Requires the following:

//Weather images
//condition
//temperature
//precipitation chance and amount
//wind speed and direction


@Composable
fun CurrentWeather(mainViewModel: MainViewModel) {
    val weather by mainViewModel.weather.collectAsState()
    val current = weather?.current
    if (current != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
                .padding(vertical = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(current.imageId),
                    contentDescription = current.condition,
                    modifier = Modifier.size(200.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = current.condition)
                Text(text = "Temperature: ${current.temperature}")
                Text(text = "Precipitation: ${current.precipitationAmount} (${current.precipitationType})")
                Text(text = "Wind: ${current.wind}")
            }
        }
    } else {
        Text(text = "No weather data available.", modifier = Modifier.padding(16.dp))
    }
}