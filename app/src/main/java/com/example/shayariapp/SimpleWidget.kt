package com.example.shayariapp

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.color.DynamicThemeColorProviders.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.shayariapp.data.db.ShayariDatabase
import com.example.shayariapp.data.db.ShayariDatabase_Impl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class SimpleWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val shayari = fetchRandomShayari(context)
        provideContent {
            Column(
                modifier = GlanceModifier.fillMaxSize().background(Color.Black),
                verticalAlignment = Alignment.Vertical.CenterVertically,
                horizontalAlignment = Alignment.Horizontal.CenterHorizontally
            ) {
                Text(
                    text = shayari, style =
                    TextStyle(
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Serif,
                        color = ColorProvider(Color.White),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = GlanceModifier.padding(10.dp)
                )
                Button(text = "Click Me", onClick = { actionStartActivity<MainActivity>() })

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

















