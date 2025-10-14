package com.example.weatherapp.models

data class Weather(
    val current: Current,
    val forecast: List<Forecast>
)

data class Current(
    // Weather Image
    val imageId: Int,
    //Condition
    val condition: String,
    //Temperature
    val temperature: String,
    //Precipitation Type and Amount
    val precipitationType: String,
    val precipitationAmount: String,
    //Wind Direction and Speed
    val wind: String,
)

data class Forecast(
    //date
    val date: String,
    //weather image
    val imageId: Int,
    //temperature high and low
    val temperatureHigh: String,
    val temperatureLow: String,
    //condition
    val condition: String,
    //precipitation type, amount,and probability
    val precipitationType: String,
    val precipitationAmount: String,
    val precipitationProbability: String,
    //wind direction and speed
    val wind: String,
    //humidity
    val humidity: String
)