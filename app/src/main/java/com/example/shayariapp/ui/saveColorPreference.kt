package com.example.shayariapp.ui

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.shayariapp.ui.theme.CustomColors

private const val PREFS_NAME = "AppPreferences"
private const val KEY_PRIMARY_COLOR = "primary_color"
private const val KEY_SECONDARY_COLOR = "secondary_color"
private const val KEY_BACKGROUND_COLOR = "background_color"
private const val KEY_SURFACE_COLOR = "surface_color"

fun saveColorPreference(context: Context, customColors: CustomColors) {
    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putInt(KEY_PRIMARY_COLOR, customColors.primary.toArgb())
    editor.putInt(KEY_SECONDARY_COLOR, customColors.secondary.toArgb())
    editor.putInt(KEY_BACKGROUND_COLOR, customColors.background.toArgb())
    editor.putInt(KEY_SURFACE_COLOR, customColors.surface.toArgb())
    editor.apply()
}

fun loadColorPreference(context: Context): CustomColors {
    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return CustomColors(
        primary = Color(sharedPreferences.getInt(KEY_PRIMARY_COLOR, Color(0xFFd0ba98).toArgb())),
        secondary = Color(sharedPreferences.getInt(KEY_SECONDARY_COLOR, Color(0xFFd8caa9).toArgb())),
        background = Color(sharedPreferences.getInt(KEY_BACKGROUND_COLOR, Color.Black.toArgb())),
        surface = Color(sharedPreferences.getInt(KEY_SURFACE_COLOR, Color.White.toArgb()))
    )
}

fun Color.toArgb() = android.graphics.Color.argb(alpha, red, green, blue)
