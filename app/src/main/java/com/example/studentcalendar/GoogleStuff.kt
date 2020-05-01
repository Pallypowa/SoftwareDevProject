package com.example.studentcalendar.com.example.studentcalendar

import android.accounts.Account
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


val HTTP_TRANSPORT= AndroidHttp.newCompatibleTransport()
val JSON_FACTOTY= JacksonFactory.getDefaultInstance()
val SHARED_PREF="sharedPref"

fun SIKER(con: Context , acc: GoogleSignInAccount?){
    Log.i("SIKER","SIKER")
    CoroutineScope(Dispatchers.IO).launch { mainFunc(con,acc?.account) }
    Log.i("Siker","VEGE")

}

suspend fun mainFunc(con: Context , acc: Account?){
    val calList= getCalendarList(con,acc)
    Log.i("calList",calList.toString())

    val id= getCalendarID(con,calList)
    var eventList=getEventList(con,acc,id)
    Log.i("Events:", eventList.toString())
    Log.i("GOT ID",id.toString())

    writeDB(con,eventList!!)
    checkDB(con)
    updateDB(con,"VEMIINB154H")
    checkDB(con)

}

fun getCredential(con:Context, acc: Account?):GoogleAccountCredential{
    var  credential= GoogleAccountCredential.usingOAuth2(
        con, Collections.singleton("https://www.googleapis.com/auth/calendar.readonly"))
    return credential.setSelectedAccount((acc))
}

suspend fun getCalendarID(con:Context,calList:CalendarList):String?{
    val sharedPreferences=con.getSharedPreferences("com.example.studentcalendar.com.example.studentcalendar",android.content.Context.MODE_PRIVATE)
    var id= sharedPreferences.getString(SHARED_PREF,null)
    if(id==null){
        id= CoroutineScope(Dispatchers.Main).async { chooseCalendar(con,calList,sharedPreferences) }.await()
    }
    return id
}

suspend fun chooseCalendar(con:Context,calList: CalendarList,shd:SharedPreferences):String?{
    var calendars= mutableMapOf<String,String>()
    var chosenCalendar:String?=null
    for(i in calList.items) calendars.put(i.summary,i.id)
    val alertDialogBuilder= AlertDialog.Builder(con)
    alertDialogBuilder.setTitle("Valassz naptarat")
    val keyList=calendars.keys.toTypedArray()
    alertDialogBuilder.setItems(keyList, DialogInterface.OnClickListener { dialog, which ->
        chosenCalendar= calendars[keyList[which]].toString()
        shd.edit().putString(SHARED_PREF,chosenCalendar).apply()
        Log.i("Calendar id", chosenCalendar)
    })
    val dialog=alertDialogBuilder.create()
    dialog.show()
    return chosenCalendar
}

suspend fun getCalendarList(con: Context, acc: Account?):CalendarList{

    var result=""
    try {
        val service= Calendar.Builder(HTTP_TRANSPORT,JSON_FACTOTY, getCredential(con,acc))
            .setApplicationName("DUMMY APP")
            .build()
        var calendarListResponse=service.CalendarList().list().execute()
        result= calendarListResponse.toString()
    }catch (e: Exception){
        e.printStackTrace()
    }
    return Gson().fromJson(result,CalendarList::class.java)
}

suspend fun  getEventList(con: Context,acc:Account?,  calID:String?):EventList?{
    var result:EventList?=null
    var cal=java.util.Calendar.getInstance()
    val minDate=DateTime(cal.time)
    cal.add(java.util.Calendar.DAY_OF_YEAR,7)
    val maxDate=DateTime(cal.time)
    Log.i("MINDATE",minDate.toStringRfc3339())
    Log.i("MAXDATE",maxDate.toStringRfc3339())
    try {
        var  credential=GoogleAccountCredential.usingOAuth2(
            con, Collections.singleton("https://www.googleapis.com/auth/calendar.readonly"))
        credential.setSelectedAccount((acc))

        val service=Calendar.Builder(HTTP_TRANSPORT,JSON_FACTOTY,credential)
            .setApplicationName("DUMMY APP")
            .build()
        var calendarListResponse=service.events().list(calID)
            .setOrderBy("startTime")
            .setTimeMin(minDate)
            .setTimeMax(maxDate)
            .setSingleEvents(true)
            .execute()
            result= Gson().fromJson(calendarListResponse.toString(),EventList::class.java)

        for(item in result.items){
            item.nepID=item.summary.substringAfter('(').substringBefore(')')
        }
    }catch (e: Exception){
        e.printStackTrace()
    }
    return result
}

fun signIn(activity: Activity,SignInClient: GoogleSignInClient , RequestCode: Int){
    val signInIntent=SignInClient.signInIntent
    startActivityForResult(activity,signInIntent,RequestCode,null)
}

fun handleSignInResult(compTask: Task<GoogleSignInAccount>): GoogleSignInAccount?{
    var acc: GoogleSignInAccount?=null
    try{
        acc= compTask.getResult(ApiException::class.java)
    }catch (e: ApiException){
        Log.i("Failed code=",e.statusCode.toString())
    }
    return acc
}

fun writeDB(con: Context, events: EventList):SQLiteDatabase?{
    var myDB:SQLiteDatabase?=null
    try{
        myDB=con.openOrCreateDatabase("HIANYZASOK",Context.MODE_PRIVATE,null)
        myDB.execSQL("CREATE TABLE IF NOT EXISTS hianyzasok ( targy TEXT PRIMARY KEY, hianyzas INT, memo TEXT)")
        for(event in events.items){
            val id=event.nepID
            var query="INSERT OR IGNORE INTO hianyzasok(targy, hianyzas, memo) VALUES ('${id}', 0, 'a')"
            myDB.execSQL(query)
        }
    }catch (e:Exception){
        e.printStackTrace()
    }
    return myDB
}

fun checkDB(con:Context){
    try {
        val myDB=con.openOrCreateDatabase("HIANYZASOK",Context.MODE_PRIVATE,null)
        val cursor= myDB.rawQuery("SELECT * FROM hianyzasok",null)
        val targyIndex= cursor.getColumnIndex("targy")
        val hianyzasIndex=cursor.getColumnIndex("hianyzas")
        val memoIndex=cursor.getColumnIndex("memo")

        cursor.moveToFirst()
        while(cursor !=null){
            println("TARGY: ${cursor.getString(targyIndex)} -HIANYZASOK: ${cursor.getString(hianyzasIndex)} -MEMO:${cursor.getString(memoIndex)}")
            cursor.moveToNext()
        }

        myDB.execSQL("DROP TABLE IF EXISTS hianyzasok")
    }catch (e:Exception){
        e.printStackTrace()
    }


}

fun updateDB(con:Context, id:String){
    try {
        val myDB=con.openOrCreateDatabase("HIANYZASOK",Context.MODE_PRIVATE,null)
        val queryUPDATE="UPDATE hianyzasok SET hianyzas=hianyzas+1 WHERE targy='${id}'"
        myDB.execSQL(queryUPDATE)
    }catch (e:Exception){
        e.printStackTrace()
    }
}









