package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shayariapp.navcontrol.routes.ProfileRouteScreen

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(top = 10.dp))

        Text(text = "Profile Screen", fontSize = 30.sp, fontWeight = W700)
        Spacer(modifier = modifier.padding(top = 10.dp))

        Button(onClick = { navController.navigate(ProfileRouteScreen.SavedPosts.route) }) {
            Text(text = "Saved Screen")
        }
        Spacer(modifier = modifier.padding(top = 10.dp))
        Button(onClick = { navController.navigate(ProfileRouteScreen.Settings.route) }) {
            Text(text = "Setting Screen")
        }
        Spacer(modifier = modifier.padding(top = 10.dp))
        Button(onClick = { navController.navigate(ProfileRouteScreen.EditProfile.route) }) {
            Text(text = "Edit Profile Screen")
        }
        Spacer(modifier = modifier.padding(top = 10.dp))
    }
}