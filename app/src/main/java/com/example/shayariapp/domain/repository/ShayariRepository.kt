package com.example.shayariapp.domain.repository

import com.example.shayariapp.data.db.ShayariEntity
import com.example.shayariapp.data.model.ShayariDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShayariRepository @Inject constructor(private val shayariDao: ShayariDao) {

    fun getShayariByGenre(genre: String): Flow<List<ShayariEntity>> {
        return shayariDao.getShayariByGenre(genre)
    }

    fun getShayariById(id: String): Flow<ShayariEntity?> {
        return shayariDao.getShayariById(id)
    }

    fun getAllQuote(): Flow<List<ShayariEntity>> = shayariDao.getShayari()

    fun getBookmarkedQuotes(): Flow<List<ShayariEntity>> = shayariDao.getBookmarkedQuotes()
    suspend fun updateQuote(quote: ShayariEntity) {
        shayariDao.updateQuote(quote)
    }

}

