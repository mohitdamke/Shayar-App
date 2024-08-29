package com.example.shayariapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shayariapp.data.db.ShayariEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShayariDao {

    @Query("SELECT * FROM shayari_table WHERE genre = :genre")
    fun getShayariByGenre(genre: String): Flow<List<ShayariEntity>>

    // Fetch Shayari by ID
    @Query("SELECT * FROM shayari_table WHERE id = :id")
    fun getShayariById(id: String): Flow<ShayariEntity?>


    @Query("SELECT * FROM shayari_table")
    fun getShayari(): Flow<List<ShayariEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShayari(shayari: ShayariEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShayariList(shayari: List<ShayariEntity>)

    @Update
    suspend fun updateQuote(shayari: ShayariEntity)

    @Query("SELECT * FROM shayari_table WHERE isBookmarked = 1")
    fun getBookmarkedQuotes(): Flow<List<ShayariEntity>>

    @Query("SELECT * FROM shayari_table WHERE text LIKE :query")
    fun searchQuotes(query: String): Flow<List<ShayariEntity>>

    @Query("SELECT * FROM shayari_table WHERE genre LIKE :query")
    fun searchQuotesGenre(query: String): Flow<List<ShayariEntity>>


}