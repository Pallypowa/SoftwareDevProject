package com.example.studentcalendar.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.studentcalendar.Fragments.*

internal class PagerAdapter (fm: FragmentManager?): FragmentPagerAdapter(fm!!){


    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> {
                SearchFragment()
            }
            1 -> {
                HomeFragment()
            }
            2 -> {
                NotificationsFragment()
            }
            else -> HomeFragment()

        }
    }

    override fun getCount(): Int {

        return 3
    }

}