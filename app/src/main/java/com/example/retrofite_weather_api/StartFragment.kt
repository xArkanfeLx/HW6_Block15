package com.example.retrofite_weather_api

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameTV: TextView = view.findViewById(R.id.nameTV)
        val infoTV: TextView = view.findViewById(R.id.infoTV)
        val imgIV: ImageView = view.findViewById(R.id.imgIV)
        val startBTN: Button = view.findViewById(R.id.startBTN)
        val viewPagerItem = arguments?.getSerializable("vp") as OnBoardingFragmetViewPagerModel

        nameTV.text = viewPagerItem.name
        infoTV.text = viewPagerItem.info
        imgIV.setImageResource(viewPagerItem.img)
        if(viewPagerItem.needBtn) {
            startBTN.visibility = View.VISIBLE
            startBTN.setOnClickListener{
                startActivity(Intent(activity,WeatherActivity::class.java))
            }
        }
    }
}