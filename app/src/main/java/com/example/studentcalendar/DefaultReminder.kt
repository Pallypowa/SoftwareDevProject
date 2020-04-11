package com.example.studentcalendar.com.example.studentcalendar

import com.google.gson.annotations.SerializedName

data class DefaultReminders (

    @SerializedName("method") val method : String,
    @SerializedName("minutes") val minutes : Int
)
