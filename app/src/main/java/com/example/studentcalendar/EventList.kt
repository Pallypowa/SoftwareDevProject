package com.example.studentcalendar.com.example.studentcalendar

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class EventList (

    @SerializedName("kind") val kind : String ,
    @SerializedName("etag") val etag : String ,
    @SerializedName("summary") val summary : String ,
    @SerializedName("updated") val updated : String ,
    @SerializedName("timeZone") val timeZone : String ,
    @SerializedName("accessRole") val accessRole : String ,
    @SerializedName("defaultReminders") val defaultReminders : List<DefaultReminders> ,
    @SerializedName("nextPageToken") val nextPageToken : String ,
    @SerializedName("items") val items : List<EventItems>
)

data class EventItems (

	@SerializedName("kind") val kind : String ,
	@SerializedName("etag") val etag : String ,
	@SerializedName("id") val id : String ,
	@SerializedName("status") val status : String ,
	@SerializedName("htmlLink") val htmlLink : String ,
	@SerializedName("created") val created : String ,
	@SerializedName("updated") val updated : String ,
	@SerializedName("summary") val summary : String ,
	@SerializedName("location") val location : String ,
	@SerializedName("creator") val creator : Creator ,
	@SerializedName("organizer") val organizer : Organizer ,
	@SerializedName("start") val start : Start ,
	@SerializedName("end") val end : End ,
	@SerializedName("visibility") val visibility : String ,
	@SerializedName("iCalUID") val iCalUID : String ,
	@SerializedName("sequence") val sequence : Int ,
	@SerializedName("reminders") val reminders : Reminders,
	var nepID:String
)

data class Creator (

	@SerializedName("email") val email : String,
	@SerializedName("displayName") val displayName : String,
	@SerializedName("self") val self : Boolean
)

data class Organizer (

	@SerializedName("email") val email : String,
	@SerializedName("displayName") val displayName : String,
	@SerializedName("self") val self : Boolean
)

data class Start (

	@SerializedName("dateTime") val dateTime : String
)

data class End (

	@SerializedName("dateTime") val dateTime : String
)

data class Reminders (

	@SerializedName("useDefault") val useDefault : Boolean
)