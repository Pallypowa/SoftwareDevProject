package com.example.studentcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextClock
import androidx.viewpager.widget.ViewPager
import com.example.studentcalendar.Adapter.PagerAdapter



class MainActivity : AppCompatActivity() {

    //Clock
    private lateinit var textclock: TextClock

    // Buttons
    private lateinit var homeBtn:ImageButton
    private lateinit var notiBtn:ImageButton
    private lateinit var searchBtn:ImageButton

    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerViewAdapter: PagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init clock

        // init views
        mViewPager = findViewById(R.id.mViewPager)
        homeBtn = findViewById(R.id.homeBtn)
        searchBtn = findViewById(R.id.searchBtn)
        notiBtn = findViewById(R.id.notificationsBtn)


        //onclick listener

        searchBtn.setOnClickListener {
            mViewPager.currentItem = 0

        }

        homeBtn.setOnClickListener {

            mViewPager.currentItem = 1

        }

        notiBtn.setOnClickListener {
            mViewPager.currentItem = 3

        }


        mPagerViewAdapter = PagerAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerViewAdapter
        mViewPager.offscreenPageLimit = 3



        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeTabs(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        mViewPager.currentItem = 1
        homeBtn.setImageResource(R.drawable.ic_home_purple)

    }

    private fun changeTabs(position: Int) {


        if (position == 0) {
            searchBtn.setImageResource(R.drawable.ic_calendar_purple)
            homeBtn.setImageResource(R.drawable.ic_home_black)
            notiBtn.setImageResource(R.drawable.ic_assessment_black)
        }
        if (position == 1) {
            searchBtn.setImageResource(R.drawable.ic_calendar_black)
            homeBtn.setImageResource(R.drawable.ic_home_purple)
            notiBtn.setImageResource(R.drawable.ic_assessment_black)
        }
        if (position == 2) {
            searchBtn.setImageResource(R.drawable.ic_calendar_black)
            homeBtn.setImageResource(R.drawable.ic_home_black)
            notiBtn.setImageResource(R.drawable.ic_assessment_purple)

        }
    }


}