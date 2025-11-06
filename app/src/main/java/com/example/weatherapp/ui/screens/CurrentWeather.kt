// kotlin
package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CurrentWeather(mainViewModel: MainViewModel) {
    val weather by mainViewModel.weather.collectAsState()
    val current = weather?.current
    val location = weather?.location


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (current != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Showing Local weather for ${location?.name ?: "Unknown"}${if (!location?.region.isNullOrBlank()) ", ${location?.region}" else ""}",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    androidx.compose.foundation.Image(
                        painter = rememberAsyncImagePainter(current.imageId),
                        contentDescription = current.condition,
                        modifier = Modifier.size(200.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = current.condition,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = "Temperature: ${current.temperature}",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.95f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Precipitation: ${current.precipitationAmount} (${current.precipitationType})",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Wind: ${current.wind}",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    weather?.forecast?.forEach { forecast ->
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            androidx.compose.foundation.Image(
                                painter = rememberAsyncImagePainter(forecast.imageId),
                                contentDescription = forecast.condition,
                                modifier = Modifier.size(80.dp)
                            )
                            Text(
                                text = forecast.date,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "High: ${forecast.temperatureHigh}",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "Low: ${forecast.temperatureLow}",
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                    }
                }
            }


        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No weather data available.",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
