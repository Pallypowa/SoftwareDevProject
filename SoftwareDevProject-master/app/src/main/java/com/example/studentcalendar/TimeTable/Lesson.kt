package com.example.studentcalendar.TimeTable

import java.sql.Time
import java.util.*

data class Lesson(val className: String , val startTime: Time , val endTime: Time , val day: String, val buildingName: String)