package com.example.shayariapp.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.shayariapp.data.db.ShayariDatabase
import com.example.shayariapp.notification.sendQuoteNotification
import kotlinx.coroutines.flow.first
import kotlin.random.Random

class ShayariWidgetWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val widgetManager = GlanceAppWidgetManager(applicationContext)
        val widgetIds = widgetManager.getGlanceIds(SimpleWidget::class.java)

        // Fetch a random Shayari
        val dao = ShayariDatabase.getDatabase(applicationContext).getShayariDao()
        val shayariList = dao.getShayari().first()
        val randomShayari = if (shayariList.isNotEmpty()) {
            shayariList[Random.nextInt(shayariList.size)].text
        } else {
            "No Shayari available."
        }

        // Update all instances of the widget
        widgetIds.forEach { glanceId ->
            SimpleWidget().updateAll(applicationContext)
        }
        sendQuoteNotification(applicationContext, randomShayari)
        return Result.success()
    }
}
