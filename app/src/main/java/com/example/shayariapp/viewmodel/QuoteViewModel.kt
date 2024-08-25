package com.example.shayariapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shayariapp.data.db.QuoteEntity
import com.example.shayariapp.domain.repository.QuoteRepository
import com.example.shayariapp.notification.sendQuoteNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private var repository: QuoteRepository,

) : ViewModel() {

    private val _quoteList = MutableStateFlow<List<QuoteEntity>>(emptyList())
    val quoteList: StateFlow<List<QuoteEntity>> = _quoteList

    private val _bookmarkedQuotes = MutableStateFlow<List<QuoteEntity>>(emptyList())
    val bookmarkedQuotes: StateFlow<List<QuoteEntity>> = _bookmarkedQuotes

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            repository.getAllQuote().collect { quotes ->
                _quoteList.value = quotes
            }
        }
    }

    // Function to save a quote
    fun saveOrUnsaveQuote(quote: QuoteEntity, context: Context) {
        viewModelScope.launch {
            // Toggle the isBookmarked property
            _quoteList.value = _quoteList.value.map { it ->
                if (it.id == quote.id) it.copy(isBookmarked = !quote.isBookmarked) else it
            }

            // Update quote in repository
            repository.updateQuote(quote.copy(isBookmarked = !quote.isBookmarked))

            // Send notification
            sendQuoteNotification(context, quote.text ?: "", quote.author ?: "")
        }
    }
    fun getBookmarkedQuotes() {
        viewModelScope.launch {
            repository.getBookmarkedQuotes().collect { quotes ->
                _bookmarkedQuotes.value = quotes.filter { it.isBookmarked }
            }
        }
    }

}

