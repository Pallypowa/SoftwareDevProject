package com.example.studentcalendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.studentcalendar.Adapters.PagerAdapter
import com.example.studentcalendar.com.example.studentcalendar.SIKER
import com.example.studentcalendar.com.example.studentcalendar.handleSignInResult
import com.example.studentcalendar.com.example.studentcalendar.signIn
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope


class MainActivity : AppCompatActivity() {

    // Buttons
    private lateinit var homeBtn:ImageButton
    private lateinit var statBtn:ImageButton
    private lateinit var calendarBtn:ImageButton

    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerViewAdapter: PagerAdapter

    //signIn stuff
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN=9001
    //private lateinit var Gaccount: GoogleSignInAccount



    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //signIn stuff
        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("77984558619-jdl0jodu9ud54atfipuq0gj4oa9ac6te.apps.googleusercontent.com")
            .requestScopes(Scope("https://www.googleapis.com/auth/calendar.readonly"))
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)
        Log.i("Place: ","Client created")

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

    override fun onStart() {
        super.onStart()
        Log.i("Place: ","in onStart")
        val Gaccount=GoogleSignIn.getLastSignedInAccount(this)
        if(Gaccount==null){
            //Not logged in
            Log.i("Place: ","Not logged in")
            signIn(this,mGoogleSignInClient,RC_SIGN_IN)


        }else{
            //loggen in
            Log.i("Place: ","Already logged in")
            SIKER(this, Gaccount)
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RC_SIGN_IN){
            val task=GoogleSignIn.getSignedInAccountFromIntent(data)
            val Gaccount=handleSignInResult(task)
            SIKER(this,Gaccount)
        }
    }

}
/*TODO
  Kérdések:
  -Ha a MainActivity-t átváltjuk a MAP gombbal MapsActivityre, akkor a MainActivity kódja fog-e futni?
  -HomeFragment-ben Intent-en belül MapsActivity konstruktorába átadni valamit.
  TODO
 */