package com.example.weather_app.Weather_App.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    companion object {
        private lateinit var apiService: ApiService

        fun getApiService(): ApiService {

            if (!Companion::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService
        }
    }
}