package com.example.shayariapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shayariapp.data.db.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes_table")
    fun getQuotes(): Flow<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotesList(quotes: List<QuoteEntity>)

    @Update
    suspend fun updateQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes_table WHERE isBookmarked = 1")
    fun getBookmarkedQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quotes_table WHERE text LIKE :query")
    fun searchQuotes(query: String): Flow<List<QuoteEntity>>


}