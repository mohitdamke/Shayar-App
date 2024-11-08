package com.example.shayariapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import com.example.shayariapp.ui.theme.CustomColors
import com.example.shayariapp.ui.theme.defaultColor // Make sure to define a default color if not already defined
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ColorPreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("color_preferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Save color preferences
    fun saveColor(customColors: CustomColors) {
        val editor = sharedPreferences.edit()
        val colorJson = gson.toJson(customColors)
        editor.putString("selected_colors", colorJson)
        editor.apply()
    }

    // Get saved color preferences or default if none saved
    fun getSavedColor(): CustomColors? {
        val colorJson = sharedPreferences.getString("selected_colors", null)
        return if (colorJson != null) {
            val type = object : TypeToken<CustomColors>() {}.type
            gson.fromJson(colorJson, type)
        } else {
            defaultColor // Use your predefined default color
        }
    }
}
