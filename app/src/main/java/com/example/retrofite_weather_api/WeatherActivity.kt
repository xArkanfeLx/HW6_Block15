package com.example.retrofite_weather_api

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retrofite_weather_api.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class WeatherActivity : AppCompatActivity() {

    private lateinit var toolbarTB:Toolbar

    private lateinit var cityET:EditText
    private lateinit var getWeatherBTN:Button

    private lateinit var infoLL:LinearLayout
    private lateinit var cityTV:TextView
    private lateinit var temperatureTV:TextView
    private lateinit var weatherIV:ImageView
    private lateinit var sideWindTV:TextView
    private lateinit var speedWindTV:TextView
    private lateinit var pressureTV:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()

        getWeatherBTN.setOnClickListener{
            val city = cityET.text
            if(city.isNotEmpty()) {
                getCurrentWeather(city.toString())
            }
            city.clear()
        }


    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    private fun getCurrentWeather(city:String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(
                    city,
                    "metric",
                    applicationContext.getString(R.string.api_key)
                )
            } catch (e:IOException){
                Toast.makeText(applicationContext,"Ошибка...",Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e:HttpException){
                Toast.makeText(applicationContext,"Ошибка сайта",Toast.LENGTH_SHORT).show()
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    infoLL.visibility = View.VISIBLE
                    val data = response.body()
                    cityTV.text = data!!.name
                    temperatureTV.text = "${data.main.temp}℃"
                    sideWindTV.text = "${data.wind.deg}°"
                    speedWindTV.text = "${data.wind.speed}м/с"
                    val iconId = data.weather[0].icon
                    val imageUrl = "https://openweathermap.org/img/wn/$iconId@4x.png"
                    Picasso.get().load(imageUrl).into(weatherIV)
                    val convertPressure = (data.main.pressure / 1.33). toInt()
                    pressureTV.text="$convertPressure мм Рс"
                }
            } else {
                infoLL.visibility = View.INVISIBLE
            }
        }
    }

    private fun init(){
        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        cityET = findViewById(R.id.cityET)
        getWeatherBTN = findViewById(R.id.getWeatherBTN)

        infoLL = findViewById(R.id.infoLL)
        cityTV = findViewById(R.id.cityTV)
        temperatureTV = findViewById(R.id.temperatureTV)
        weatherIV = findViewById(R.id.weatherIV)
        sideWindTV = findViewById(R.id.sideWindTV)
        speedWindTV = findViewById(R.id.speedWindTV)
        pressureTV = findViewById(R.id.pressureTV)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}