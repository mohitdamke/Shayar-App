package com.example.shayariapp.di

import android.content.Context
import com.example.shayariapp.data.db.ShayariDatabase
import com.example.shayariapp.data.model.ShayariDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): ShayariDatabase {
        return ShayariDatabase.getDatabase(context)
    }

    @Provides
    fun provideQuoteDao(database: ShayariDatabase): ShayariDao {
        return database.getShayariDao()
    }

}