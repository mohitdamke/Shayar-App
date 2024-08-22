package com.example.shayariapp.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shayariapp.data.db.QuoteEntity

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quotes: List<QuoteEntity>)

    @Query("SELECT * FROM quotes")
    fun getQuotes(): LiveData<List<QuoteEntity>>

    @Update
    suspend fun updateQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes WHERE isSaved = 1 ORDER BY id ASC")
    fun getSavedQuotes(): LiveData<List<QuoteEntity>>

    @Query("SELECT * FROM quotes WHERE id = :id")
    suspend fun getQuoteById(id: Int): QuoteEntity?

}