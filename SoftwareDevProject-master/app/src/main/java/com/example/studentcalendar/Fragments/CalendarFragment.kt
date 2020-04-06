package com.example.studentcalendar.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentcalendar.R
import com.github.tlaabs.timetableview.TimetableView

/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)
        return rootView
    }



}
