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
    private lateinit var arrowBack : ImageButton
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

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

        arguments?.let {
            val weatherData = it.getString("WeatherModel")
            val city = it.getString("city")
            weatherData?.let { safeWeatherData ->
                val weatherModel = Gson().fromJson(safeWeatherData, WeatherModel::class.java)

                cityName.text =city
                temperature.text = weatherModel.daily?.get(0)?.temp?.day.toString()
               /* highest.text = weatherModel.daily?.get(0)?.temp?.max.toString() + "\u00B0"
                lowest.text = weatherModel.daily?.get(0)?.temp?.min.toString()  + "\u00B0"*/
                description.text= weatherModel.current?.weather?.get(0)?.description
                sunset.text = weatherModel.daily?.get(0)?.sunset.toString()
                sunrise.text = weatherModel.daily?.get(0)?.sunrise.toString()
                wind.text = weatherModel.current?.windSpeed.toString()
                humidity.text = weatherModel.current?.humidity.toString()
                uvi.text = weatherModel.current?.uvi.toString()

            }
        }
        /** Navigation (going back) for arrow back button **/
        arrowBack.setOnClickListener {
            navController.navigate(R.id.action_fragment_weather_detail_to_fragment_weather, Bundle().apply {
            })
        }
    }
}