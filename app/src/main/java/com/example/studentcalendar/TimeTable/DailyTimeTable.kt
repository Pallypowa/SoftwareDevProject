package com.example.studentcalendar.TimeTable

class DailyTimeTable(private val lessons: List<Lesson>){
    private lateinit var daysLessons: List<Lesson>

    fun getDaysLessons(day: String): List<Lesson>{
        for (element in lessons){
            if(element.day == day){
                /*TODO*/
            }
        }
        return daysLessons
    }
}