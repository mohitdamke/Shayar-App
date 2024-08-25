package com.example.shayariapp.presentation.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shayariapp.viewmodel.QuoteViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val context = LocalContext.current
    val viewModel: QuoteViewModel = hiltViewModel()
    val quotes by viewModel.quoteList.collectAsState(initial = emptyList())
    var searchQuery by remember { mutableStateOf("") }



    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(top = 10.dp))
        Text(text = "Quotes Screen", fontSize = 30.sp, fontWeight = FontWeight.W700)
        Spacer(modifier = modifier.padding(top = 10.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
                viewModel.searchQuotes(newValue)
            },
            label = { Text("Search your quotes") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyColumn(modifier = modifier.fillMaxSize()) {
            if (quotes.isEmpty()) {
                item {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = modifier.padding(top = 10.dp))
                        Text(text = "Quotes Screen", fontSize = 30.sp, fontWeight = FontWeight.W700)
                        Spacer(modifier = modifier.padding(top = 10.dp))
                        Log.e(
                            "Data Fetch TAG",
                            "First successfully pre-populated quote into database"
                        )
                        Log.e(
                            "Data Fetch TAG",
                            "Second successfully pre-populated quote into database"
                        )
                        // Show a loading indicator while quotes are being loaded

                        Text(
                            text = "Loading quotes...",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                items(items = quotes) { quote ->
                    QuoteItem(
                        quote = quote.text,
                        author = quote.author,
                        isBookmarked = quote.isBookmarked,
                        onSaveClicked = { viewModel.saveOrUnsaveQuote(quote, context) })

                }
            }
        }

        LaunchedEffect(key1 = quotes) {
            Log.e("Data Fetch TAG", "Quotes loaded with count: ${quotes.size}")

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
    val context = LocalContext.current
    val clipboardManager: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(
                    onClick = onSaveClicked,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = if (isBookmarked) "Unsave" else "Save")
                }
                IconButton(
                    onClick = {
                        val clip = ClipData.newPlainText(
                            "Quote",
                            "$quote - $author"
                        )
                        clipboardManager.setPrimaryClip(clip)
                        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy to clipboard"
                    )
                }
                IconButton(
                    onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "$quote - $author")
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share quote"
                    )
                }
            }
        }
    }
}
