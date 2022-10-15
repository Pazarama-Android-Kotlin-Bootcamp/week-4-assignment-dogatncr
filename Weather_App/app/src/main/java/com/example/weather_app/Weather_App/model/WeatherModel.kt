package com.example.weather_app.Weather_App.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/** Data class for notes information with Json conversion feature **/
data class WeatherModel(
    @SerializedName("current")
    var current: Current?,
    @SerializedName("daily")
    var daily: List<Daily>?,
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lon")
    var lon: Double?,
    @SerializedName("timezone")
    var timezone: String?,
    @SerializedName("timezone_offset")
    var timezoneOffset: Int?
) {
    fun toJson(): String? {
        return Gson().toJson(this)
    }
    fun fromJson(jsonValue: String): WeatherModel {
        return Gson().fromJson(jsonValue, WeatherModel::class.java)
    }
}
