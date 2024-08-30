package com.example.shayariapp.widget

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.shayariapp.MainActivity
import com.example.shayariapp.data.db.ShayariDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class SimpleWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val shayari = fetchRandomShayari(context)
        actionStartActivity<MainActivity>()
        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize()
                    .background(GlanceTheme.colors.widgetBackground),
                verticalAlignment = Alignment.Vertical.CenterVertically,
                horizontalAlignment = Alignment.Horizontal.CenterHorizontally
            ) {
                Text(
                    text = shayari,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = GlanceTheme.colors.onSurface
                    ),
                    modifier = GlanceModifier.padding(10.dp)
                )
            }
        }
    }
}

private fun fetchRandomShayari(context: Context): String {
    return runBlocking {
        val dao = ShayariDatabase.getDatabase(context).getShayariDao()
        val shayariList = dao.getShayari().first()
        if (shayariList.isNotEmpty()) {
            shayariList[Random.nextInt(shayariList.size)].text
        } else {
            "No Shayari available."
        }
    }
}


class TestWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = SimpleWidget()
}

















