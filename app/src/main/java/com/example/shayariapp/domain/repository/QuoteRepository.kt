package com.example.shayariapp.domain.repository

import com.example.shayariapp.data.db.QuoteEntity
import com.example.shayariapp.data.model.QuoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteDao: QuoteDao) {

    fun getAllQuote() : Flow<List<QuoteEntity>> = quoteDao.getQuotes()

    fun getBookmarkedQuotes(): Flow<List<QuoteEntity>> = quoteDao.getBookmarkedQuotes()
    suspend fun updateQuote(quote: QuoteEntity) {
        quoteDao.updateQuote(quote)
    }

    // Function to get all saved quotes
    fun getSavedQuotes(): Flow<List<QuoteEntity>> = quoteDao.getQuotes()
}

