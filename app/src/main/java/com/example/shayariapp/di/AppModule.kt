package com.example.shayariapp.di

import android.content.Context
import com.example.shayariapp.data.db.QuoteDatabase
import com.example.shayariapp.data.model.QuoteDao
import com.example.shayariapp.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return QuoteDatabase.getDatabase(context)
    }

    @Provides
    fun provideQuoteDao(database: QuoteDatabase): QuoteDao {
        return database.getQuoteDao()
    }

}