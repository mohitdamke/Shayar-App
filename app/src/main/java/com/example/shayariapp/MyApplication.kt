package com.example.shayariapp

import android.app.Application
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shayariapp.notification.createNotificationChannel
import com.example.shayariapp.viewmodel.QuoteViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
}