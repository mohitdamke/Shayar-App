package com.example.shayariapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shayariapp.presentation.screen.HomeScreen
import com.example.shayariapp.presentation.screen.Privacy
import com.example.shayariapp.presentation.screen.Saved
import com.example.shayariapp.presentation.screen.SettingScreen
import com.example.shayariapp.presentation.screen.ShayariDetailScreen
import com.example.shayariapp.presentation.screen.ShayariList
import com.example.shayariapp.presentation.screen.SplashScreen

@Composable
fun NavigationControl() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Routes.ShayariList.route) {
            val genre = it.arguments?.getString("genre") ?: ""
            ShayariList(navController = navController, genre = genre)
        }
        composable(Routes.ShayariDetail.route) {
            val shayariId = it.arguments?.getString("shayariId") ?: ""

            ShayariDetailScreen(navController = navController, shayariId = shayariId)
        }
        composable(Routes.Saved.route) {
            Saved(navController = navController)
        }
        composable(Routes.Privacy.route) {
            Privacy(navController = navController)
        }
        composable(Routes.Setting.route) {
            SettingScreen(navController = navController)
        }
    }


}