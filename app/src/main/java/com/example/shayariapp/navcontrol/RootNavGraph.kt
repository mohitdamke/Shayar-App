package com.example.shayariapp.navcontrol

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shayariapp.navcontrol.graph.homeNavGraph
import com.example.shayariapp.navcontrol.graph.profileNavGraph
import com.example.shayariapp.navcontrol.graph.splashNavGraph
import com.example.shayariapp.navcontrol.routes.Graph
import com.example.shayariapp.presentation.screen.MainScreen

@Composable
fun RootNavGraph() {
    val rootNavController: NavHostController = rememberNavController()

    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.MainScreenGraph
    ) {
        splashNavGraph(rootNavController = rootNavController)

        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController = rootNavController)
        }

        homeNavGraph(rootNavController = rootNavController)

        profileNavGraph(rootNavController = rootNavController)
    }
}