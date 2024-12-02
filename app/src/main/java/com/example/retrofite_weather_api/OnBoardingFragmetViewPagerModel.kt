package com.example.retrofite_weather_api

import java.io.Serializable

class OnBoardingFragmetViewPagerModel(val name:String,val info:String,val img:Int,val needBtn:Boolean) :Serializable {
    companion object{
        val listVP = mutableListOf(
            OnBoardingFragmetViewPagerModel(
                "Приветствуем!",
                "Добро пожаловать в приложение погода!",
                R.drawable.img1,
                false),
            OnBoardingFragmetViewPagerModel(
                "О приложении",
                "Чётко и быстро определит погоду!",
                R.drawable.img2,
                false),
            OnBoardingFragmetViewPagerModel(
                "Пользуйтесь!",
                "Будьте всегда готовы к изменениям в погоде!",
                R.drawable.img3,
                true)
        )
    }
}