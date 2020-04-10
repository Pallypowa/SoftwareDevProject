package com.example.studentcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextClock
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.studentcalendar.Adapters.PagerAdapter
import com.example.studentcalendar.Adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_statistics.*


class MainActivity : AppCompatActivity() {

    // Buttons
    private lateinit var homeBtn:ImageButton
    private lateinit var statBtn:ImageButton
    private lateinit var calendarBtn:ImageButton

    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerViewAdapter: PagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Coords reading
        val reader = JsonReader()
        reader.getJsonDataFromAsset(applicationContext, "coords.json")

        // init views
        mViewPager = findViewById(R.id.mViewPager)
        homeBtn = findViewById(R.id.homeBtn)
        calendarBtn = findViewById(R.id.calendarBtn)
        statBtn = findViewById(R.id.statBtn)

        //onclick listener

        calendarBtn.setOnClickListener {
            mViewPager.currentItem = 0
        }

        homeBtn.setOnClickListener {
            mViewPager.currentItem = 1
        }

        statBtn.setOnClickListener {
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
            calendarBtn.setImageResource(R.drawable.ic_calendar_purple)
            homeBtn.setImageResource(R.drawable.ic_home_black)
            statBtn.setImageResource(R.drawable.ic_assessment_black)
        }
        if (position == 1) {
            calendarBtn.setImageResource(R.drawable.ic_calendar_black)
            homeBtn.setImageResource(R.drawable.ic_home_purple)
            statBtn.setImageResource(R.drawable.ic_assessment_black)
        }
        if (position == 2) {
            calendarBtn.setImageResource(R.drawable.ic_calendar_black)
            homeBtn.setImageResource(R.drawable.ic_home_black)
            statBtn.setImageResource(R.drawable.ic_assessment_purple)

        }
    }


}
/*TODO
  Kérdések:
  -Ha a MainActivity-t átváltjuk a MAP gombbal MapsActivityre, akkor a MainActivity kódja fog-e futni?
  -HomeFragment-ben Intent-en belül MapsActivity konstruktorába átadni valamit.
  TODO
 */