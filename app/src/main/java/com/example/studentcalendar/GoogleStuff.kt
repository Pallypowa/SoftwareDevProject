package com.example.studentcalendar.com.example.studentcalendar

import android.accounts.Account
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
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
import com.google.api.services.calendar.Calendar
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.util.*


val HTTP_TRANSPORT= AndroidHttp.newCompatibleTransport()
val JSON_FACTOTY= JacksonFactory.getDefaultInstance()

fun SIKER(con: Context , acc: GoogleSignInAccount?){
    Log.i("SIKER","SIKER")
    CoroutineScope(Dispatchers.IO).launch { APIcalls(con,acc?.account) }

}

suspend fun APIcalls(con: Context, acc: Account?){
    val calList= getCalendarList(con,acc)
    Log.i("calList",calList.toString())
    val id= CoroutineScope(Dispatchers.Main).async { chooseCalendar(con,calList) }.await()
    val eventList=getEventList(con,acc,id)
    Log.i("Events:", eventList)
}

fun getCredential(con:Context, acc: Account?):GoogleAccountCredential{
    var  credential= GoogleAccountCredential.usingOAuth2(
        con, Collections.singleton("https://www.googleapis.com/auth/calendar.readonly"))
    return credential.setSelectedAccount((acc))
}

suspend fun chooseCalendar(con:Context,calList: CalendarList):String?{
    var calendars= mutableMapOf<String,String>()
    var chosenCalendar:String?=null
    for(i in calList.items) calendars.put(i.summary,i.id)
    val alertDialogBuilder= AlertDialog.Builder(con)
    alertDialogBuilder.setTitle("Valassz naptarat")
    val keyList=calendars.keys.toTypedArray()
    alertDialogBuilder.setItems(keyList, DialogInterface.OnClickListener { dialog, which ->
        chosenCalendar= calendars[keyList[which]].toString()
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

suspend fun  getEventList(con: Context,acc:Account?,  calID:String?):String{
    var result=""
    try {
        var  credential=GoogleAccountCredential.usingOAuth2(
            con, Collections.singleton("https://www.googleapis.com/auth/calendar.readonly"))
        credential.setSelectedAccount((acc))

        val service=Calendar.Builder(HTTP_TRANSPORT,JSON_FACTOTY,credential)
            .setApplicationName("DUMMY APP")
            .build()
        var calendarListResponse=service.events().list(calID).execute()
        result= calendarListResponse.toString()
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
        val acc= compTask.getResult(ApiException::class.java)
    }catch (e: ApiException){
        Log.i("Failed code=",e.statusCode.toString())
    }
    return acc
}









