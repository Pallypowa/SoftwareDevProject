package com.example.studentcalendar

import android.content.Context
import org.json.JSONArray
import java.io.IOException

class JsonReader {

    private val buildings = mutableMapOf<String, Pair<Double, Double>>()

    fun getJsonDataFromAssetOriginal(context: Context , fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        }
        catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getJsonDataFromAsset(context: Context, fileName: String) {
        val jsonString: String

        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

            var jsonarr = JSONArray(jsonString)

            for(i in 0..jsonarr.length() - 1){
                var jsonObj = jsonarr.getJSONObject(i)

                buildings.put(jsonObj.getString("name"),
                    Pair(jsonObj.getDouble("long"), jsonObj.getDouble("lat")))

                //arr.add(jsonObj.getString("I"))
            }
            print(buildings.keys + " " + buildings.values)

        }
        catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    fun getMap(): MutableMap<String, Pair<Double, Double>>{
        return this.buildings
    }
}