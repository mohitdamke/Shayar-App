package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shayariapp.viewmodel.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Saved(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val context = LocalContext.current
    val viewModel: QuoteViewModel = hiltViewModel()
    val bookmarkedQuotes by viewModel.bookmarkedQuotes.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getBookmarkedQuotes()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Saved Quotes", fontSize = 30.sp, fontWeight = W600)
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(bookmarkedQuotes) { quote ->
                QuoteItem(
                    quote = quote.text,
                    author = quote.author,
                    isBookmarked = quote.isBookmarked,
                    onSaveClicked = {
                        viewModel.saveOrUnsaveQuote(quote, context)
                    }
                )
            }
        }
    }
}


@Composable
private fun QuoteItem(
    quote: String,
    author: String,
    isBookmarked: Boolean,
    onSaveClicked: () -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = quote, fontSize = 24.sp,
            )
            Text(
                text = author, fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = onSaveClicked,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = if (isBookmarked) "Unsave" else "Save")
            }
        }
    }
}
