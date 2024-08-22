package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Setting(modifier: Modifier = Modifier, navController: NavController) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Setting Screen")
    }
}