package com.example.retrofiteweatherapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.retrofiteweatherapi.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class SecondActivity : AppCompatActivity() {

    private lateinit var toolbarTB:Toolbar
    private lateinit var inputCityET: TextView
    private lateinit var getWeatherInfoBTN:Button
    private lateinit var temperatureTV: TextView
    private lateinit var temperatureMinTV:TextView
    private lateinit var temperatureMaxTV:TextView
    private lateinit var humidityTV: TextView
    private lateinit var visibilityTV: TextView
    private lateinit var windSpeedTV: TextView
    private lateinit var pressureTV: TextView
    private lateinit var weatherIV:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        init()

        getWeatherInfoBTN.setOnClickListener {
            getCurrentWeather()
        }
    }

    private fun init() {
        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)
        inputCityET = findViewById(R.id.inputCityET)
        getWeatherInfoBTN = findViewById(R.id.getWeatherInfoBTN)
        temperatureTV = findViewById(R.id.temperatureTV)
        temperatureMinTV = findViewById(R.id.temperatureMinTV)
        temperatureMaxTV = findViewById(R.id.temperatureMaxTV)
        humidityTV = findViewById(R.id.humidityTV)
        visibilityTV = findViewById(R.id.visibilityTV)
        windSpeedTV = findViewById(R.id.windSpeedTV)
        pressureTV = findViewById(R.id.pressureTV)
        weatherIV = findViewById(R.id.weatherIV)
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(
                    inputCityET.text.toString(), "metric", applicationContext.getString(R.string.api_key)
                )
            } catch (e: IOException) {
                Toast.makeText(
                    applicationContext,
                    "app error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(
                    applicationContext,
                    "http error ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()
                    temperatureTV.text = "${data!!.main.temp.toInt()}°C"
                    temperatureMinTV.text = "Min: ${data.main.temp_min}°C"

                    if (data.main.temp_min != data.main.temp_max){
                        temperatureMaxTV.text = "Max: ${data.main.temp_max}°C"
                    }else temperatureMaxTV.text = ""

                    windSpeedTV.text = "Скорость ветра: ${data.wind.speed}m/sec"
                    visibilityTV.text = "Видимость: ${data.visibility}m"
                    humidityTV.text = "Влажность: ${data.main.humidity}%"

                    val iconId = data.weather[0].icon
                    Log.d("TAGGGGGGGGG", iconId)
                    val imageUrl = "https://openweathermap.org/img/wn/$iconId@2x.png"
                    Log.d("Tagggg",imageUrl)
                    Picasso.get().load(imageUrl).into(weatherIV)

                    val currentPressure = (data.main.pressure / 1.33).toInt()
                    pressureTV.text = "Атм.давление: $currentPressure mm Hg"
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.exitItem -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}