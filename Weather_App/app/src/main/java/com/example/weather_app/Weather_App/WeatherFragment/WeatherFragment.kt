package com.example.weather_app.Weather_App.WeatherFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.Weather_App.data.ApiClient
import com.example.weather_app.Weather_App.model.WeatherModel
import retrofit2.Call
import retrofit2.Response

class WeatherFragment: Fragment(), WeatherListener{
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var weatherList : MutableList<WeatherModel>
    private lateinit var cityList : MutableList<String>

    /** Creating weather views fragment **/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)

    }

    /** Initialized navigation controller, created function for getting data for cities and called set up adapter **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        weatherRecyclerView = view.findViewById(R.id.weatherList)
        weatherList = mutableListOf()
        cityList= mutableListOf()
        getIstanbul()
    }
    private fun getLondon(){
        ApiClient.getApiService().getWeatherLon().enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    weather?.let { weather_safe ->
                        weatherList.add(weather)
                        cityList.add("London")
                    }
                    getNy()
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("hata","fail")
            }
        })
    }
    private fun getRome(){
        ApiClient.getApiService().getWeatherRom().enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    weather?.let { weather_safe ->
                        weatherList.add(weather)
                        cityList.add("Rome")
                    }
                    getTokio()
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("hata","fail")
            }
        })
    }
    private fun getTokio(){
        ApiClient.getApiService().getWeatherTok().enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    weather?.let { weather_safe ->
                        weatherList.add(weather)
                        cityList.add("Tokio")
                    }
                }
                setupAdapter()
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("hata","fail")
            }
        })
    }
    private fun getNy(){
        ApiClient.getApiService().getWeatherNY().enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    weather?.let { weather_safe ->
                        weatherList.add(weather)
                        cityList.add("New York")
                    }
                    getRome()
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("hata","fail")
            }
        })
    }
    private fun getIstanbul() {
        ApiClient.getApiService().getWeatherIst().enqueue(object : retrofit2.Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    weather?.let { weather_safe ->
                        weatherList.add(weather)
                        cityList.add("Istanbul")
                    }
                    getLondon()
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("hata","fail")
            }
        })
    }
    /** Setting up adapter for weather recycler view **/
    private fun setupAdapter() {
        weatherRecyclerView.adapter = WeatherAdapter(weatherList, cityList,this)

    }

    /** Defining onClick method for weather elements **/
    override fun onClicked(weatherModel: WeatherModel,city:String) {
        navController.navigate(R.id.action_fragment_weather_to_detail, Bundle().apply {
            putString("WeatherModel", weatherModel.toJson())
            putString("city",city)
        })
    }
}