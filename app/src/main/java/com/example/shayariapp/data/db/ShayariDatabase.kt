package com.example.shayariapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shayariapp.data.model.PrepopulateRoomCallback
import com.example.shayariapp.data.model.ShayariDao

@Database(entities = [ShayariEntity::class], version = 1, exportSchema = false)
abstract class ShayariDatabase : RoomDatabase() {
    abstract fun getShayariDao(): ShayariDao

    companion object {
        @Volatile
        private var DB_INSTANCE: ShayariDatabase? = null

        fun getDatabase(context: Context): ShayariDatabase {
            return DB_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShayariDatabase::class.java,
                    "shayari_database"
                ).addCallback(PrepopulateRoomCallback(context))
                    .build()
                DB_INSTANCE = instance
                instance
            }
        }
    }

}
