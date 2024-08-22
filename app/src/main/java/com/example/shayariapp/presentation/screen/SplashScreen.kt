package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shayariapp.R
import com.example.shayariapp.navcontrol.routes.Graph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.navigate(Graph.MainScreenGraph) {
            popUpTo(Graph.SplashGraph) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
        )
    }
}
//        Text(
//            text = "FZ",
//            fontSize = 100.sp,
//            fontWeight = FontWeight.Bold,
//            fontStyle = FontStyle.Italic ,
//            color = Blue40
//        )
//        Text(text = "FriendZone",
//            fontSize = 26.sp,
//            fontWeight = FontWeight.Normal,
//            fontStyle = FontStyle.Normal ,
//            color = Blue40, letterSpacing = 10.sp)
//

