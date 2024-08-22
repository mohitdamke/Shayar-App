package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shayariapp.navcontrol.bottom_navigation.BottomNavigationBar
import com.example.shayariapp.navcontrol.graph.MainNavGraph
import com.example.shayariapp.viewmodel.QuoteViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController = rememberNavController(),
    homeNavController: NavHostController = rememberNavController()
) {

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = homeNavController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                MainNavGraph(
                    rootNavController = rootNavController,
                    homeNavController = homeNavController
                )
            }
        },

        )
}