package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shayariapp.common.ShimmerEffect
import com.example.shayariapp.navigation.Routes
import com.example.shayariapp.ui.theme.Gray100
import com.example.shayariapp.ui.theme.Gray40
import com.example.shayariapp.viewmodel.ShayariViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {

    val viewModel: ShayariViewModel = hiltViewModel()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val isLoading by viewModel.isLoading.observeAsState(true)


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Shayar",
                        maxLines = 1,
                        letterSpacing = 1.sp, fontSize = 38.sp,
                        overflow = TextOverflow.Visible,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif,
                        color = White
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Routes.Saved.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Localized description",
                            modifier = modifier.size(28.dp)
                        )
                    }
                    IconButton(onClick = { navController.navigate(Routes.Setting.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Setting",
                            modifier = modifier.size(28.dp)
                        )
                    }
                }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Gray100,
                    titleContentColor = Gray100,
                    actionIconContentColor = White,
                    navigationIconContentColor = White,
                    scrolledContainerColor = Gray100
                ),
                scrollBehavior = scrollBehavior
            )
        },
    ) { padding ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(Gray100),
            contentPadding = PaddingValues(2.dp)
        ) {
            if (isLoading) {
                items(11) { // Show a few shimmer cards while loading
                    ShimmerGenreCard()
                }
            } else {
                // Display the genres when loading is complete
                items(
                    listOf(
                        "Love", "Friendship", "Motivation", "Sadness", "Happiness",
                        "Romance", "Life", "Patriotism", "Good Morning", "Good Night",
                        "Festivals", "Heartbreak", "Humor", "Success", "Nature",
                        "Spiritual", "Celebration", "Memories", "Wisdom", "Family"
                    )
                ) { genre ->
                    ShayariGenre(
                        genre = genre,
                        navController = navController
                    )
                }
            }
        }
    }
}


@Composable
private fun ShayariGenre(
    modifier: Modifier = Modifier,
    genre: String,
    navController: NavController
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .height(60.dp)
            .clickable {
                navController.navigate(
                    Routes.ShayariList.route.replace("{genre}", genre)
                )
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Gray40),

        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = modifier.padding(start = 10.dp))
            Text(
                text = "$genre Shayari",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = White,
                fontStyle = FontStyle.Normal,
                modifier = modifier
            )
            Spacer(modifier = modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "ArrowForwardIos",
                modifier = modifier.size(30.dp), tint = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerGenreCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .height(60.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Gray100),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            ShimmerEffect(
                modifier = Modifier.fillMaxSize()
                    .weight(1f)
                    .background(Gray100, RoundedCornerShape(8.dp))
            )
        }
    }
}
