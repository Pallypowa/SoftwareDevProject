package com.example.studentcalendar.com.example.studentcalendar

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class CalendarList (

    @SerializedName("etag") val etag : String ,
    @SerializedName("items") val items : List<CalendarItems> ,
    @SerializedName("kind") val kind : String ,
    @SerializedName("nextSyncToken") val nextSyncToken : String
)

data class CalendarItems (

    @SerializedName("accessRole") val accessRole : String ,
    @SerializedName("backgroundColor") val backgroundColor : String ,
    @SerializedName("colorId") val colorId : Int ,
    @SerializedName("conferenceProperties") val conferenceProperties : ConferenceProperties ,
    @SerializedName("defaultReminders") val defaultReminders : List<DefaultReminders> ,
    @SerializedName("etag") val etag : String ,
    @SerializedName("foregroundColor") val foregroundColor : String ,
    @SerializedName("id") val id : String ,
    @SerializedName("kind") val kind : String ,
    @SerializedName("selected") val selected : Boolean ,
    @SerializedName("summary") val summary : String ,
    @SerializedName("summaryOverride") val summaryOverride : String ,
    @SerializedName("timeZone") val timeZone : String

)


data class ConferenceProperties (

	@SerializedName("allowedConferenceSolutionTypes") val allowedConferenceSolutionTypes : List<String>
)