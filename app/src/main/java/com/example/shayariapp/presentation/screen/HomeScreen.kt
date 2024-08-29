package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shayariapp.R
import com.example.shayariapp.navigation.Routes
import com.example.shayariapp.viewmodel.ShayariViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {


    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val viewModel: ShayariViewModel = hiltViewModel()
    val shayari by viewModel.quoteList.collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()
    val listState = rememberLazyGridState()


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Shayar",
                        maxLines = 1,
                        letterSpacing = 1.sp, fontSize = 38.sp,
                        overflow = TextOverflow.Visible,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Cursive,
                        color = Black
                    )
                },
                actions =  {
                    IconButton(onClick = { navController.navigate(Routes.Saved.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Bookmark,
                            contentDescription = "Localized description",
                            modifier = modifier.size(30.dp)
                        )
                    }
                    IconButton(onClick = { navController.navigate(Routes.Setting.route) }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Setting",
                            modifier = modifier.size(30.dp)
                        )
                    }
                }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                scrollBehavior = scrollBehavior
            )
        },
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (shayari.isEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Shayari is loading",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                LazyVerticalGrid(
                    state = listState,
                    contentPadding = PaddingValues(top = 1.dp),
                    columns = GridCells.Adaptive(190.dp),
                ) {
                    items(
                        listOf(
                            "Love" to R.drawable.a,
                            "Friendship" to R.drawable.b,
                            "Motivation" to R.drawable.c,
                            "Sadness" to R.drawable.d,
                            "Happiness" to R.drawable.e,
                            "Romance" to R.drawable.f,
                            "Life" to R.drawable.g,
                            "Patriotism" to R.drawable.h,
                            "Good Morning" to R.drawable.i,
                            "Good Night" to R.drawable.j,
                            "Festivals" to R.drawable.k,
                            "Heartbreak" to R.drawable.l,
                            "Humor" to R.drawable.m,
                            "Success" to R.drawable.n,
                            "Nature" to R.drawable.o,
                            "Spiritual" to R.drawable.p,
                            "Celebration" to R.drawable.q,
                            "Memories" to R.drawable.r,
                            "Wisdom" to R.drawable.s,
                            "Family" to R.drawable.t
                        )

                    ) { (genre, image) ->
                        ShayariGenre(
                            genre = genre,
                            image = image,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun ShayariGenre(
    modifier: Modifier = Modifier,
    genre: String,
    image: Int,
    navController: NavController
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White),
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .height(130.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = "image",
                modifier = modifier
                    .size(190.dp)
                    .clickable {
                        navController.navigate(
                            Routes.ShayariList.route.replace("{genre}", genre)
                        )
                    },
                contentScale = ContentScale.Crop,
            )

            Text(
                text = genre,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                color = White,
                fontStyle = FontStyle.Italic,
                modifier = modifier
                    .align(Alignment.Center)
                    .shadow(elevation = 10.dp, ambientColor = Black, spotColor = White)
            )
        }

    }
}






















