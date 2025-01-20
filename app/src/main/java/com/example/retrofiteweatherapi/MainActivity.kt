package com.example.retrofiteweatherapi

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofiteweatherapi.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var cityTV:TextView
    private lateinit var temperatureTV:TextView
    private lateinit var weatherIV:ImageView
    private lateinit var windDegreeTV:TextView
    private lateinit var windSpeedTV:TextView
    private lateinit var pressureTV:TextView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        getCurrentWeather()
    }

    private fun init() {
        cityTV = findViewById(R.id.cityTV)
        temperatureTV = findViewById(R.id.temperatureTV)
        weatherIV = findViewById(R.id.weatherIV)
        windDegreeTV = findViewById(R.id.windDegreeTV)
        windSpeedTV = findViewById(R.id.windSpeedTV)
        pressureTV = findViewById(R.id.pressureTV)
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(
                    "москва","metric",applicationContext.getString(R.string.api_key)
                )
            }catch (e:IOException){
                Toast.makeText(
                    applicationContext,
                    "app error ${e.message}",
                    Toast.LENGTH_SHORT).show()
                return@launch
            }catch (e:HttpException){
                Toast.makeText(
                    applicationContext,
                    "http error ${e.message}",
                    Toast.LENGTH_SHORT).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    val data = response.body()
                    cityTV.text = data!!.name
                    temperatureTV.text = "${data.main.temp}°C"
                    windDegreeTV.text = "${data.wind.deg}°"
                    windSpeedTV.text = "${data.wind.speed}m/sec"
                    val iconId = data.weather[0].icon
                    val imageUrl = "https://openweathermap.org/img/wn/$iconId@2x.png"
                    Picasso.get().load(imageUrl).into(weatherIV)
                    val currentPressure = (data.main.pressure/1.33).toInt()
                    pressureTV.text = "$currentPressure mm Hg"
                }
            }
        }
    }
}