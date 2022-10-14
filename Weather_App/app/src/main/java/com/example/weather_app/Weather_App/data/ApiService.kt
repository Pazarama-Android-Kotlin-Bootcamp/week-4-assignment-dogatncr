package com.example.weather_app.Weather_App.data

import com.example.weather_app.Weather_App.model.WeatherModel
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("onecall?lat=41.01&lon=28.97&exclude=hourly,minutely&appid=8ddadecc7ae4f56fee73b2b405a63659&units=metric")
    fun getWeatherIst(): Call<WeatherModel>
    @GET("onecall?lat=41.9&lon=12.49&exclude=hourly,minutely&appid=8ddadecc7ae4f56fee73b2b405a63659&units=metric")
    fun getWeatherRom(): Call<WeatherModel>
    @GET("onecall?lat=51.5&lon=-0.11&exclude=hourly,minutely&appid=8ddadecc7ae4f56fee73b2b405a63659&units=metric")
    fun getWeatherLon(): Call<WeatherModel>
    @GET("onecall?lat=40.73&lon=-73.93&exclude=hourly,minutely&appid=8ddadecc7ae4f56fee73b2b405a63659&units=metric")
    fun getWeatherNY(): Call<WeatherModel>
    @GET("onecall?lat=35.65&lon=139.83&exclude=hourly,minutely&appid=8ddadecc7ae4f56fee73b2b405a63659&units=metric")
    fun getWeatherTok(): Call<WeatherModel>

}
