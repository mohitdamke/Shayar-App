package com.example.shayariapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shayari_table")
data class ShayariEntity(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "genre")   val genre: String,
    @ColumnInfo(name = "isBookmarked") var isBookmarked: Boolean = false

) : java.io.Serializable