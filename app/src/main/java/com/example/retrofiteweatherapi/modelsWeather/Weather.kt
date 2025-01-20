package com.example.retrofiteweatherapi.modelsWeather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)