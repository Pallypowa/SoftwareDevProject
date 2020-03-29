package com.example.studentcalendar.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.studentcalendar.Fragments.*

internal class PagerAdapter (fm: FragmentManager?): FragmentPagerAdapter(fm!!){


    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> {
                CalendarFragment()
            }
            1 -> {
                HomeFragment()
            }
            2 -> {
                StatisticsFragment()
            }
            else -> HomeFragment()

        }
    }

    override fun getCount(): Int {

        return 3
    }

}