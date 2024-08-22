package com.example.shayariapp.navcontrol.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shayariapp.navcontrol.routes.Graph
import com.example.shayariapp.navcontrol.routes.SplashRouteScreen
import com.example.shayariapp.presentation.screen.SplashScreen

fun NavGraphBuilder.splashNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.SplashGraph, startDestination = SplashRouteScreen.Splash.route
    ) {
        composable(
            route = SplashRouteScreen.Splash.route
        ) {
            SplashScreen(navController = rootNavController)
        }

    }
}