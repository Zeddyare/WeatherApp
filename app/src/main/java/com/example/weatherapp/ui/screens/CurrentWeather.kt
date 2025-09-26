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

//Weather images
//condition
//temperature
//precipitation chance and amount
//wind speed and direction

@Preview
@Composable
fun CurrentWeather() {
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
        ) {
            Image(
                painter = painterResource(id = R.drawable.snowing),
                contentDescription = "Snowy weather"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Snowing"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Currently: -2°C\nFeels like -10°C\nPrecipitation: 100%\nWind: NE 35km/h\n",

                )
            }
        }
    }
}