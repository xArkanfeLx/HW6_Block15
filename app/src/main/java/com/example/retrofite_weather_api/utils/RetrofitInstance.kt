package com.example.retrofite_weather_api.utils

import com.example.retrofite_weather_api.ApiWeatherInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:ApiWeatherInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Util.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiWeatherInterface::class.java)
    }
}