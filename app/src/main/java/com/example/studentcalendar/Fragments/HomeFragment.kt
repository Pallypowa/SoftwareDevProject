package com.example.studentcalendar.Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentcalendar.MapsActivity
import com.example.studentcalendar.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        rootView.mapBtn.setOnClickListener{
            val intent = Intent(this.context, MapsActivity::class.java)
            startActivity(intent)
        }
        return rootView
    }


}
