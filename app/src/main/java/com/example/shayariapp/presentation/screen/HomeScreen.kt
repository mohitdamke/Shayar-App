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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shayariapp.data.db.QuoteEntity
import com.example.shayariapp.viewmodel.QuoteViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val context = LocalContext.current
    val viewModel: QuoteViewModel = hiltViewModel()
    val quotes = viewModel.quotes.observeAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadQuotesFromJson(context)
    }
    LazyColumn(modifier = modifier) {
        item {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.padding(top = 10.dp))
                Text(text = "Quotes Screen", fontSize = 30.sp, fontWeight = FontWeight.W700)
                Spacer(modifier = modifier.padding(top = 10.dp))

                this@LazyColumn.items(quotes.value ?: emptyList()) { quote ->
                    QuoteItem(quote = quote, onSaveClicked = { viewModel.saveQuote(it) })

                }
            }
        }
    }

}

@Composable
private fun QuoteItem(quote: QuoteEntity, onSaveClicked: (QuoteEntity) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            Color.White
        )
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
