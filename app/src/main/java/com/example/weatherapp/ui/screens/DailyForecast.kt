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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R


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

@Preview
@Composable
fun DailyForecast() {
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(vertical = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Halifax, Nova Scotia"
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.snowing),
                contentDescription = "Snowy weather",
                modifier = Modifier.size(200.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Monday, Dec 23\nHigh: -2°C Low: -10°C\nCondition: Snowing\nPrecipitation: 80% chance, 5cm\nWind: NW at 15 km/h\nHumidity: 85%"
                )
            }

        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.sunny),
                contentDescription = "Sunny weather",
                modifier = Modifier.size(200.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Monday, Dec 23\nHigh: -2°C Low: -10°C\nCondition: Snowing\nPrecipitation: 80% chance, 5cm\nWind: NW at 15 km/h\nHumidity: 85%"
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.rainy),
                contentDescription = "Rainy weather",
                modifier = Modifier.size(200.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Monday, Dec 23\nHigh: -2°C Low: -10°C\nCondition: Snowing\nPrecipitation: 80% chance, 5cm\nWind: NW at 15 km/h\nHumidity: 85%"
                )
            }
        }
    }
}