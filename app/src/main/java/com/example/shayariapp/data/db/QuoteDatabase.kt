package com.example.shayariapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shayariapp.data.PrepopulateRoomCallback
import com.example.shayariapp.data.model.QuoteDao

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao

    companion object {
        @Volatile
        private var DB_INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            return DB_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java,
                    "Quote_DB"
                ).addCallback(PrepopulateRoomCallback(context))
                    .build()
                DB_INSTANCE = instance
                instance
            }

        }
    }

}
