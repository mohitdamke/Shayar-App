package com.example.shayariapp.navcontrol.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shayariapp.navcontrol.routes.Graph
import com.example.shayariapp.navcontrol.routes.HomeRouteScreen
import com.example.shayariapp.presentation.screen.ShayariDetail

fun NavGraphBuilder.homeNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.HomeGraph, startDestination = HomeRouteScreen.ShayariDetail.route
    ) {
        composable(
            route = HomeRouteScreen.ShayariDetail.route
        ) {
            val quote = it.arguments!!.getString("quote")
            val author = it.arguments!!.getString("author")

            ShayariDetail(navController = rootNavController, quote = quote!!, author = author!!)
        }
    }
}