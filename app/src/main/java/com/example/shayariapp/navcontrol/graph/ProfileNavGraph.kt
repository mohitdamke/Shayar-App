package com.example.shayariapp.navcontrol.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shayariapp.navcontrol.routes.Graph
import com.example.shayariapp.navcontrol.routes.ProfileRouteScreen
import com.example.shayariapp.presentation.screen.EditProfile
import com.example.shayariapp.presentation.screen.Saved
import com.example.shayariapp.presentation.screen.Setting

fun NavGraphBuilder.profileNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.ProfileGraph, startDestination = ProfileRouteScreen.EditProfile.route
    ) {

        composable(
            route = ProfileRouteScreen.EditProfile.route
        ) {
            EditProfile(navController = rootNavController)
        }

        composable(
            route = ProfileRouteScreen.Settings.route
        ) {
            Setting(navController = rootNavController)
        }
        composable(
            route = ProfileRouteScreen.SavedPosts.route
        ) {
            Saved(navController = rootNavController)
        }
    }
}