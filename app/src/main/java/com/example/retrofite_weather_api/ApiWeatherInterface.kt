package com.example.retrofite_weather_api

import com.example.retrofite_weather_api.models.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherInterface {
    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q")city:String,
        @Query("utils")units:String,
        @Query("appid") apiKey:String
    ):Response<CurrentWeather>
}