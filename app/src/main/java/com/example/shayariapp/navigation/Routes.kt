package com.example.shayariapp.navigation

sealed class Routes (val route: String ){
    object Splash: Routes("splash")
    object Home: Routes("home")
    object Saved: Routes("saved")
    object ShayariList: Routes("shayari_list/{genre}")
    object ShayariDetail: Routes("shayari_detail/{shayariId}")
    object Privacy: Routes("privacy")
    object Setting: Routes("setting")
}