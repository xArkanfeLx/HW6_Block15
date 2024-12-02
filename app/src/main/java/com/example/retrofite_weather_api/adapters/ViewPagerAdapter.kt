package com.example.retrofite_weather_api.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofite_weather_api.OnBoardingFragmetViewPagerModel
import com.example.retrofite_weather_api.StartFragment

class ViewPagerAdapter(fragment:FragmentActivity,private val viewPagerList:MutableList<OnBoardingFragmetViewPagerModel>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int  = viewPagerList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = StartFragment()
        fragment.arguments = bundleOf("vp" to viewPagerList[position])
        return fragment
    }
}