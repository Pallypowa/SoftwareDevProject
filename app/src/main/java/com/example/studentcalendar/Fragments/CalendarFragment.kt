package com.example.studentcalendar.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentcalendar.R
import com.github.tlaabs.timetableview.Schedule
import com.github.tlaabs.timetableview.Time
import kotlinx.android.synthetic.main.fragment_calendar.view.*


class CalendarFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_calendar, container, false)


        val timetable = rootView.timetable
        val schedules = ArrayList<Schedule>()




        val schedule1 = Schedule()
        schedule1.classTitle = "Digitalis szamitas elmelete"
        schedule1.classPlace = "A01"
        schedule1.professorName = "Heckl István"
        schedule1.startTime = Time(8 , 0)
        schedule1.endTime = Time(10 , 0)
        schedules.add(schedule1)

        val schedule2 = Schedule()
        schedule2.day = 1
        schedule2.classTitle = "Szoftverfejlesztés"
        schedule2.classPlace = "I3"
        schedule2.professorName = "Frits Márton"
        schedule2.startTime = Time(14 , 0)
        schedule2.endTime = Time(17 , 0)
        schedules.add(schedule2)

        val schedule3 = Schedule()
        schedule3.day = 3
        schedule3.classTitle = "Operációs Rendszerek"
        schedule3.classPlace = "A1"
        schedule3.professorName = "Csertán György"
        schedule3.startTime = Time(14 , 0)
        schedule3.endTime = Time(18 , 0)
        schedules.add(schedule3)

        val schedule4 = Schedule()
        schedule4.day = 0
        schedule4.classTitle = "Párhuzamos Programozás"
        schedule4.classPlace = "PC0"
        schedule4.professorName = "Juhász Zoltán"
        schedule4.startTime = Time(14 , 0)
        schedule4.endTime = Time(18 , 0)
        schedules.add(schedule4)

        val schedule5 = Schedule()
        schedule5.day = 2
        schedule5.classTitle = "Adatbáziskezelő rendszerek II"
        schedule5.classPlace = "I1"
        schedule5.professorName = "Leitold Dániel"
        schedule5.startTime = Time(8 , 0)
        schedule5.endTime = Time(10 , 0)
        schedules.add(schedule5)


        timetable.add(schedules)
        return rootView
    }



}
