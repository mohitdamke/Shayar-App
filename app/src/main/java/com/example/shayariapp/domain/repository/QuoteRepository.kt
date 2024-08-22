package com.example.shayariapp.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.shayariapp.data.db.Quote
import com.example.shayariapp.data.db.QuoteDatabase
import com.example.shayariapp.data.db.QuoteEntity
import com.example.shayariapp.data.model.QuoteDao
import com.google.gson.Gson

class QuoteRepository(private val quoteDao: QuoteDao) {


    fun getQuotes(): LiveData<List<QuoteEntity>>{
        return quoteDao.getQuotes()
    }

    suspend fun saveQuote(quote: QuoteEntity) {
        quoteDao.updateQuote(quote)
    }


    fun getSavedQuotes(): LiveData<List<QuoteEntity>> {
        return quoteDao.getSavedQuotes()
    }


    suspend fun loadQuotesFromJson(context: Context, database: QuoteDatabase) {
        val jsonString = context.assets.open("quotes.json").bufferedReader().use { it.readText() }
        val quotes = Gson().fromJson(jsonString, Array<Quote>::class.java).toList()
        val quoteEntities =
            quotes.map { QuoteEntity(text = it.text, author = it.author, isSaved = false) }
        database.quoteDao().insertQuotes(quoteEntities)
    }

}
