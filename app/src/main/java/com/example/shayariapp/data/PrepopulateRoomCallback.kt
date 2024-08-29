package com.example.shayariapp.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shayariapp.R
import com.example.shayariapp.data.db.ShayariDatabase
import com.example.shayariapp.data.db.ShayariEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class PrepopulateRoomCallback(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        Log.e("Data Fetch TAG", "successfully pre-populated quote into database This 1")

        super.onCreate(db)
        Log.e("Data Fetch TAG", "successfully pre-populated quote into database This 2")

        CoroutineScope(Dispatchers.IO).launch {
            Log.e("Data Fetch TAG", "successfully pre-populated quote into database This 3")
            prePopulateUsers(context)
        }
    }
}

suspend fun prePopulateUsers(context: Context) {
    try {
        val shayariDao = ShayariDatabase.getDatabase(context).getShayariDao()
//        val json = context.resources.openRawResource(R.raw.happiness).bufferedReader().use { it.readText() }
//        val jsonArray = JSONArray(json)
//        val shayariList = mutableListOf<ShayariEntity>()
//
//        for (i in 0 until jsonArray.length()) {
//            val jsonObject = jsonArray.getJSONObject(i)
//            val text = jsonObject.getString("text")
//            val genre = jsonObject.getString("genre")
//            shayariList.add(ShayariEntity(text = text, genre = genre))
//        }
//        shayariDao.insertShayariList(shayariList)
//
//        Log.e("Data Fetch TAG", "Successfully pre-populated ${shayariList.size} quotes into the database")
        val shayariList: JSONArray = context.resources.openRawResource(R.raw.shayari).bufferedReader().use {
            JSONArray(it.readText())
        }

        Log.e("Data Fetch TAG", "JSON Data: $shayariList")

        if (shayariList.length() > 0) {
            for (index in 0 until shayariList.length()) {
                val jsonObj = shayariList.getJSONObject(index)

                // Creating QuoteEntity and inserting it into the database
                val shayariEntity = ShayariEntity(
                    text = jsonObj.getString("text"),
                    genre =  jsonObj.getString("genre")

                )
                shayariDao.insertShayari(shayariEntity)

                Log.e("Data Fetch TAG", "Inserted quote: ${shayariEntity.text}, ID: ${shayariEntity.id}")
            }
            Log.e("Data Fetch TAG", "Successfully pre-populated ${shayariList.length()} quotes into the database")
        }
    } catch (exception: Exception) {
        Log.e("Data Fetch TAG", "Failed to pre-populate quotes into database: ${exception.localizedMessage}")
    }
}

