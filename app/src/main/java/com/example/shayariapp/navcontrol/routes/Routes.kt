package com.example.shayariapp.navcontrol.routes

object Graph {

    const val SplashGraph = "splashGraph"

    const val RootGraph = "rootGraph"

    const val MainScreenGraph = "mainScreenGraph"

    const val HomeGraph = "homeGraph"

    const val ProfileGraph = "profileGraph"

}

sealed class SplashRouteScreen(val route: String) {

    object Splash : SplashRouteScreen("splash")

}


sealed class MainRouteScreen(val route: String) {

    object Home : MainRouteScreen("home")

    object Profile : MainRouteScreen("profile")

}

sealed class HomeRouteScreen(val route: String) {

    object ShayariDetail : HomeRouteScreen("shayari_detail/{quote}/{author}")

}

sealed class ProfileRouteScreen(val route: String) {

    object EditProfile : ProfileRouteScreen("edit_profile")

    object Settings : ProfileRouteScreen("settings")

    object SavedPosts : ProfileRouteScreen("saved_posts")

}
