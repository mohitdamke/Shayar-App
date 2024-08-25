package com.example.shayariapp.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shayariapp.R
import com.example.shayariapp.data.db.QuoteDatabase
import com.example.shayariapp.data.db.QuoteEntity
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
        val quoteDao = QuoteDatabase.getDatabase(context).getQuoteDao()

        val quoteList: JSONArray = context.resources.openRawResource(R.raw.quotes).bufferedReader().use {
            JSONArray(it.readText())
        }

        if (quoteList.length() > 0) {
            for (index in 0 until quoteList.length()) {
                val quoteObj = quoteList.getJSONObject(index)

                // Creating QuoteEntity and inserting it into the database
                val quoteEntity = QuoteEntity(
                    text = quoteObj.getString("text"),
                    author = quoteObj.getString("author")
                )
                quoteDao.insertQuote(quoteEntity)

                Log.e("Data Fetch TAG", "Inserted quote: ${quoteEntity.text}, ID: ${quoteEntity.id}")
            }
            Log.e("Data Fetch TAG", "Successfully pre-populated ${quoteList.length()} quotes into the database")
        }
    } catch (exception: Exception) {
        Log.e("Data Fetch TAG", "Failed to pre-populate quotes into database: ${exception.localizedMessage}")
    }
}


//suspend fun prePopulateUsers(context: Context) {
//    try {
//        val quoteDao = QuoteDatabase.getDatabase(context).getQuoteDao()
//
//        val quoteList: JSONArray =
//            context.resources.openRawResource(R.raw.quotes).bufferedReader().use {
//                JSONArray(it.readText())
//            }
//
//        quoteList.takeIf { it.length() > 0 }?.let {
//            Log.e("Data Fetch TAG", "successfully pre-populated quote into database This 4")
//            for (index in 0 until quoteList.length()) {
//                val quoteObj = quoteList.getJSONObject(index)
//                quoteDao.insertQuote(
//
//                    QuoteEntity(
//                        text = quoteObj.getString("text"),
//                        author = quoteObj.getString("author"),
//                    )
//
//                )
//                Log.e("Data Fetch TAG", "Inserted quote: ${quoteEntity.text}, ID: ${quoteEntity.id}")
//            }
//            Log.e("Data Fetch TAG", "successfully pre-populated quote into database This 6")
//        }
//    } catch (exception: Exception) {
//        Log.e(
//            "Data Fetch TAG",
//            exception.localizedMessage ?: "failed to pre-populate quote into database"
//        )
//    }
//}