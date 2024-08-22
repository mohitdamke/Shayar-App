package com.example.shayariapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.shayariapp.data.db.QuoteDatabase
import com.example.shayariapp.data.db.QuoteEntity
import com.example.shayariapp.domain.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private var repository: QuoteRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    val quotes = repository.getQuotes()

    fun saveQuote(quote: QuoteEntity) {
        viewModelScope.launch {
            try {
                Log.d("QuoteViewModel", "Saving quote: $quote")
                _loading.value = true
                val updatedQuote = quote.copy(isSaved = !quote.isSaved)
                repository.saveQuote(updatedQuote)
                _loading.value = false
            } catch (e: Exception) {
                Log.e("QuoteViewModel", "Error saving quote", e)
            }
        }
    }

    fun getSavedQuotes(): LiveData<List<QuoteEntity>> {
        return repository.getSavedQuotes()
    }

    fun loadQuotesFromJson(
        context: Context,
        quoteDatabase: QuoteDatabase = QuoteDatabase.getDatabase(context)
    ) {
        viewModelScope.launch {
            _loading.value = true
            repository.loadQuotesFromJson(context = context, database = quoteDatabase)
            _loading.value = false
        }
    }
}
