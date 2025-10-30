// kotlin
package com.example.weatherapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun DailyForecast(mainViewModel: MainViewModel) {
    val weather by mainViewModel.weather.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            weather?.forecast?.forEach { forecast ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(12.dp)
                ) {
                    androidx.compose.foundation.Image(
                        painter = rememberAsyncImagePainter(forecast.imageId),
                        contentDescription = forecast.condition,
                        modifier = Modifier.size(80.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = forecast.date,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "High: ${forecast.temperatureHigh} Low: ${forecast.temperatureLow}",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.95f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Condition: ${forecast.condition}",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Precipitation: ${forecast.precipitationProbability} chance, ${forecast.precipitationAmount}",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Wind: ${forecast.wind}",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Humidity: ${forecast.humidity}",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
