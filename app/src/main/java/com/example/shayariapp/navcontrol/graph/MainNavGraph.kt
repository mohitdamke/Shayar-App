package com.example.shayariapp.navcontrol.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shayariapp.navcontrol.routes.Graph
import com.example.shayariapp.navcontrol.routes.MainRouteScreen
import com.example.shayariapp.presentation.screen.HomeScreen
import com.example.shayariapp.presentation.screen.ProfileScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController
) {


    NavHost(
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.Home.route,
    ) {
        composable(route = MainRouteScreen.Home.route  ){
            HomeScreen(navController = rootNavController)
        }

        composable(route = MainRouteScreen.Profile.route){
            ProfileScreen(navController = rootNavController)
        }
    }
}