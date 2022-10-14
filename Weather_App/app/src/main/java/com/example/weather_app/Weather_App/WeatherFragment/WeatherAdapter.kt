package com.example.weather_app.Weather_App.WeatherFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.Weather_App.model.WeatherModel
import com.squareup.picasso.Picasso

class WeatherAdapter(
    private val WeatherList: MutableList<WeatherModel>,
    private val CityList: MutableList<String>,
    private val listener: WeatherListener
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(WeatherList[position], listener,CityList[position])
    }

    override fun getItemCount(): Int {
        return WeatherList.size
    }

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val city = view.findViewById<TextView>(R.id.Location)
        private val highest = view.findViewById<TextView>(R.id.highest)
        private val lowest = view.findViewById<TextView>(R.id.lowest)
        private val icon = view.findViewById<ImageView>(R.id.weatherIcon)

        private val picasso = Picasso.get()

        fun bind(weatherModel: WeatherModel, listener: WeatherListener, cityName: String,) {
            city.text =cityName
            highest.text = weatherModel.daily?.get(0)?.temp?.max.toString() + "\u00B0"
            lowest.text = weatherModel.daily?.get(0)?.temp?.min.toString()  + "\u00B0"
            var iconCode= weatherModel.current?.weather?.get(0)?.icon
            var url="https://openweathermap.org/img/wn/$iconCode@2x.png"
            picasso.load(url).into(icon)

            itemView.setOnClickListener {
                listener.onClicked(weatherModel,cityName)
            }

        }
    }
}
interface WeatherListener {
    fun onClicked(weatherModel: WeatherModel,city :String)
}

