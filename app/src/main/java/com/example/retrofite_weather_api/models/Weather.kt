package com.example.retrofite_weather_api.models

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)