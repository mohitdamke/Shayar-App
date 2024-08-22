package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shayariapp.data.db.QuoteEntity
import com.example.shayariapp.viewmodel.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Saved(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val viewModel: QuoteViewModel = hiltViewModel()

    val savedQuotes = viewModel.getSavedQuotes().observeAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit) {
        viewModel.getSavedQuotes()
    }

    LazyColumn(modifier = modifier) {
        item {
            Column(
                modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.padding(top = 10.dp))

                Text(text = "Saved Quotes", fontSize = 30.sp, fontWeight = FontWeight.W700)
                this@LazyColumn.items(savedQuotes.value ?: emptyList()) { quote ->
                    QuoteSavedItems(quote = quote, onSaveClicked = {
                        val updatedQuote = quote.copy(isSaved = quote.isSaved)
                        viewModel.saveQuote(updatedQuote)
                    })
                }
            }
        }
    }
}

@Composable
private fun QuoteSavedItems(quote: QuoteEntity, onSaveClicked: (QuoteEntity) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = quote.text, fontSize = 24.sp,
            )
            Text(
                text = quote.author, fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = { onSaveClicked(quote) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = if (quote.isSaved) "Unsave" else "Save")
            }
        }
    }
}
//) {
//    Scaffold(topBar = {
//        TopBarWithBack(
//            title = stringResource(R.string.text_favourites),
//            onBackClick = { navController.navigateUp() })
//
//    }, content = {paddingValues ->
//        // pass quote & author params to details card
//        when (val result = mainViewModel.favState.collectAsState().value) {
//            is FavouriteViewState.Empty -> EmptyScreen(onClick = { navController.navigateUp() })
//            is FavouriteViewState.Success -> {
//                LazyColumn {
//                    items(key = { it.id }, count = result.data.size ) { quote ->
//                        var unread by remember { mutableStateOf(false) }
//                        val dismissState = rememberDismissState(
//                            confirmStateChange = {
//                                if (it == DismissValue.DismissedToEnd) unread = !unread
//                                it != DismissValue.DismissedToEnd
//                            }
//                        )
//
//                        SwipeToDismiss(
//                            state = dismissState,
//                            modifier = Modifier.padding(vertical = 4.dp),
//                            directions = setOf(
//                                StartToEnd,
//                                DismissDirection.EndToStart
//                            ), dismissContent = { direction ->
//                                FractionalThreshold(if (direction == StartToEnd) 0.25f else 0.5f)
//                            },
//                            background = {
//                                val direction =
//                                    dismissState.dismissDirection ?: return@SwipeToDismiss
//                                val color by animateColorAsState(
//                                    when (dismissState.targetValue) {
//                                        DismissValue.Default -> MaterialTheme.colorScheme.background
//                                        DismissValue.DismissedToEnd -> Red
//                                        DismissValue.DismissedToStart -> Red
//                                    }
//                                )
//                                val alignment = when (direction) {
//                                    StartToEnd -> Alignment.CenterStart
//                                    DismissDirection.EndToStart -> Alignment.CenterEnd
//                                }
//                                val icon = when (direction) {
//                                    StartToEnd -> Icons.Outlined.Delete
//                                    DismissDirection.EndToStart -> Icons.Outlined.Delete
//                                }
//                                val scale by animateFloatAsState(
//                                    if (dismissState.targetValue == DismissValue.Default) 0.75f else 1.5f
//                                )
//
//                                Box(
//                                    Modifier
//                                        .fillMaxSize()
//                                        .background(color)
//                                        .padding(horizontal = 20.dp),
//                                    contentAlignment = alignment
//                                ) {
//                                    Icon(
//                                        icon,
//                                        contentDescription = stringResource(R.string.text_swipe_to_delete),
//                                        modifier = Modifier
//                                            .scale(scale)
//                                            .clickable { mainViewModel.deleteFavourite(quote) },
//                                        tint = Color.White
//                                    )
//                                }
//                            },
//                            dismissContent = {
//                                ListItem(
//                                    text = {
//                                        QuotesCard(quote = quote, actions = {
//                                            navController.navigate("${HomeRouteScreen.ShayariDetail.route}/$quote/$author")
//                                        })
//                                    },
//                                    secondaryText = { Text(stringResource(R.string.text_swipe_to_remvoe)) }
//                                )
//                            }
//                        )
//
//                    }
//                }
//            }
//
//            is FavouriteViewState.Error -> TODO()
//            FavouriteViewState.Loading -> TODO()
//        }
//
//    })