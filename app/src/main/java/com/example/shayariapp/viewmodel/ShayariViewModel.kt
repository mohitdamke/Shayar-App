package com.example.shayariapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shayariapp.data.db.ShayariEntity
import com.example.shayariapp.domain.repository.ShayariRepository
import com.example.shayariapp.notification.sendQuoteNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ShayariViewModel @Inject constructor(
    private var repository: ShayariRepository,

    ) : ViewModel() {

    private val _shayariList = MutableStateFlow<List<ShayariEntity>>(emptyList())
    val shayariList: StateFlow<List<ShayariEntity>> get() = _shayariList

    private val _bookmarkedShayari = MutableStateFlow<List<ShayariEntity>>(emptyList())
    val bookmarkedShayari: StateFlow<List<ShayariEntity>> = _bookmarkedShayari

    private val _shayariDetail = MutableStateFlow<ShayariEntity?>(null)
    val shayariDetail: StateFlow<ShayariEntity?> = _shayariDetail

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllQuote().collect { quotes ->
                _shayariList.value = quotes
                _isLoading.value = false

            }
        }
    }

    fun getShayariByGenre(genre: String): Flow<List<ShayariEntity>> {
        return repository.getShayariByGenre(genre)
    }

    fun getShayariById(id: String): Flow<ShayariEntity?> {
        return repository.getShayariById(id)
    }


    // Function to save a quote
    fun saveOrUnsaveQuote(quote: ShayariEntity, context: Context) {
        viewModelScope.launch {
            // Toggle the isBookmarked property
            _shayariList.value = _shayariList.value.map { it ->
                if (it.id == quote.id) it.copy(isBookmarked = !quote.isBookmarked) else it
            }

            // Update quote in repository
            repository.updateQuote(quote.copy(isBookmarked = !quote.isBookmarked))

        }
    }

    fun getBookmarkedQuotes() {
        viewModelScope.launch {
            repository.getBookmarkedQuotes().collect { quotes ->
                _bookmarkedShayari.value = quotes.filter { it.isBookmarked }
            }
        }
    }

    fun searchQuotes(query: String) {
        viewModelScope.launch {

            repository.searchQuotes(query).collect { quotes ->
                _shayariList.value = quotes
            }
        }
    }

    fun searchQuotesGenre(query: String) {
        viewModelScope.launch {
            repository.searchQuotesGenre(query).collect { quotes ->
                _shayariList.value = quotes
            }
        }
    }


}

