package com.example.shayariapp.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shayariapp.presentation.screen.ColorScreen
import com.example.shayariapp.presentation.screen.HomeScreen
import com.example.shayariapp.presentation.screen.PrivacyScreen
import com.example.shayariapp.presentation.screen.SavedScreen
import com.example.shayariapp.presentation.screen.SettingScreen
import com.example.shayariapp.presentation.screen.ShayariDetailScreen
import com.example.shayariapp.presentation.screen.ShayariListScreen
import com.example.shayariapp.presentation.screen.SplashScreen
import com.example.shayariapp.ui.loadColorPreference
import com.example.shayariapp.ui.saveColorPreference
import com.example.shayariapp.ui.theme.LocalCustomColors

@Composable
fun NavigationControl() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var customColors by remember { mutableStateOf(loadColorPreference(context)) }

    CompositionLocalProvider(LocalCustomColors provides customColors) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = customColors.primary,
                secondary = customColors.secondary,
                background = customColors.background,
                surface = customColors.surface
            )
        ) {
            NavHost(navController = navController, startDestination = Routes.Home.route) {
                composable(Routes.Splash.route) {
                    SplashScreen(navController = navController)
                }
                composable(Routes.Home.route) {
                    HomeScreen(navController = navController)
                }
                composable(Routes.ShayariList.route) {
                    val genre = it.arguments?.getString("genre") ?: ""
                    ShayariListScreen(navController = navController, genre = genre)
                }
                composable(Routes.ShayariDetail.route) {
                    val shayariId = it.arguments?.getString("shayariId") ?: ""

                    ShayariDetailScreen(navController = navController, shayariId = shayariId)
                }
                composable(Routes.Saved.route) {
                    SavedScreen(navController = navController)
                }
                composable(Routes.Privacy.route) {
                    PrivacyScreen(navController = navController)
                }
                composable(Routes.Color.route) {
                    ColorScreen(navController = navController, onColorChange = { colors ->
                        customColors = colors
                        saveColorPreference(context, colors)
                    })
                }
                composable(Routes.Setting.route) {
                    SettingScreen(
                        navController = navController,
                    )
                }
            }
        }

    }
}