package com.example.shayariapp.notification

import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.shayariapp.R
import com.example.shayariapp.data.db.ShayariDatabase
import com.example.shayariapp.notification.CHANNEL_ID
import com.example.shayariapp.notification.sendQuoteNotification
import kotlinx.coroutines.flow.first
import kotlin.random.Random

class ShayariNotificationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        // Fetch a random Shayari
        val dao = ShayariDatabase.getDatabase(applicationContext).getShayariDao()
        val shayariList = dao.getShayari().first()
        val randomShayari = if (shayariList.isNotEmpty()) {
            shayariList[Random.nextInt(shayariList.size)].text
        } else {
            "No Shayari available."
        }

        // Send the notification
        sendQuoteNotification(applicationContext, randomShayari)

        return Result.success()
    }
}
