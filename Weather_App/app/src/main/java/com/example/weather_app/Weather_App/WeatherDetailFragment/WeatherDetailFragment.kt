package com.example.weather_app.Weather_App.WeatherDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.weather_app.R
import com.example.weather_app.Weather_App.model.WeatherModel
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailFragment: Fragment(){
    private lateinit var temperature: TextView
    private lateinit var cityName: TextView
    private lateinit var description: TextView
    private lateinit var sunset: TextView
    private lateinit var sunrise: TextView
    private lateinit var humidity: TextView
    private lateinit var wind: TextView
    private lateinit var pressure: TextView
    private lateinit var uvi: TextView
    private lateinit var highest: TextView
    private lateinit var lowest: TextView
    private lateinit var arrowBack : ImageButton
    private lateinit var navController: NavController

    /** Creating weather detail fragment **/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_detail, container, false)
    }

    /** Initialized navigation controller and called set up views **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    /** Setting up weather detail views **/
    private fun setupViews(view: View) {
        temperature = view.findViewById(R.id.celcius)
        cityName = view.findViewById(R.id.address)
        description = view.findViewById(R.id.detail)
        arrowBack = view.findViewById(R.id.backArrow)
        sunrise = view.findViewById(R.id.sunrise)
        sunset = view.findViewById(R.id.sunset)
        humidity = view.findViewById(R.id.humidity)
        wind = view.findViewById(R.id.wind)
        pressure = view.findViewById(R.id.pressure)
        uvi = view.findViewById(R.id.uvi)
        highest = view.findViewById(R.id.temp_max)
        lowest = view.findViewById(R.id.temp_min)

        /** Setting weather data which was gathered from API **/
        arguments?.let {
            val weatherData = it.getString("WeatherModel")
            val city = it.getString("city")
            weatherData?.let { safeWeatherData ->
                val weatherModel = Gson().fromJson(safeWeatherData, WeatherModel::class.java)

                cityName.text =city
                temperature.text = weatherModel.daily?.get(0)?.temp?.day.toString() + "\u00B0"
                highest.text = "Max Temp.= " + weatherModel.daily?.get(0)?.temp?.max.toString() + "\u00B0"
                lowest.text = "Min Temp.= " + weatherModel.daily?.get(0)?.temp?.min.toString()  + "\u00B0"
                description.text= weatherModel.current?.weather?.get(0)?.description
                var snst = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(weatherModel.daily?.get(0)?.sunrise!!.toLong()*1000))
                var snrs = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(weatherModel.daily?.get(0)?.sunset!!.toLong()*1000))
                sunset.text = snrs
                sunrise.text = snst
                wind.text = weatherModel.current?.windSpeed.toString()
                humidity.text = weatherModel.current?.humidity.toString()
                uvi.text = weatherModel.current?.uvi.toString()
                pressure.text = weatherModel.current?.pressure.toString()

            }
        }
        /** Navigation (going back) for arrow back button **/
        arrowBack.setOnClickListener {
            navController.navigate(R.id.action_fragment_weather_detail_to_fragment_weather, Bundle().apply {
            })
        }
    }
}