package com.example.shayariapp.presentation.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shayariapp.ui.theme.Blue100
import com.example.shayariapp.ui.theme.Blue40
import com.example.shayariapp.viewmodel.ShayariViewModel

@Composable
fun ShayariDetail(
    modifier: Modifier = Modifier,
    navController: NavController,
    shayariId: String,
) {
    val context = LocalContext.current
    val viewModel: ShayariViewModel = hiltViewModel()
    val shayari by viewModel.getShayariById(shayariId).collectAsState(initial = null)
    val clipboardManager: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        shayari?.let { quote ->
            ShayariDetailItem(
                shayariId = quote.id.toString(),
                quote = quote.text ?: "",
                isBookmarked = quote.isBookmarked,
                onSaveClicked = { viewModel.saveOrUnsaveQuote(quote, context) },
                onCopy = {
                    val clip = ClipData.newPlainText(
                        "Quote",
                        quote.text
                    )
                    clipboardManager.setPrimaryClip(clip)
                    Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                        .show()
                },
                onShare = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, quote.text)
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                })
        } ?: run {
            Text(
                text = "Loading quote details...",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LaunchedEffect(key1 = shayari) {
            Log.e("Data Fetch TAG", "Quote loaded with ID: $shayariId")
        }
    }
}

@Composable
private fun ShayariDetailItem(
    shayariId: String,
    quote: String,
    isBookmarked: Boolean,
    onSaveClicked: () -> Unit,
    onShare: () -> Unit,
    onCopy: () -> Unit
) {
    Scaffold { paddingValues ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = quote,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Button(
                        onClick = onSaveClicked,
                        modifier = Modifier.padding(top = 18.dp),
                        colors = ButtonColors(
                            containerColor = Blue40,
                            contentColor = White,
                            disabledContainerColor = Blue100,
                            disabledContentColor = White
                        )
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else
                                Icons.Default.BookmarkBorder,
                            contentDescription = null, modifier = Modifier.padding(2.dp)
                        )
                    }
                    Button(
                        onClick = { onCopy() },
                        modifier = Modifier.padding(top = 18.dp),
                        colors = ButtonColors(
                            containerColor = Blue40,
                            contentColor = White,
                            disabledContainerColor = Blue100,
                            disabledContentColor = White
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy to clipboard",
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                    Button(
                        onClick = {
                            onShare()
                        },
                        modifier = Modifier.padding(top = 18.dp),
                        colors = ButtonColors(
                            containerColor = Blue40,
                            contentColor = White,
                            disabledContainerColor = Blue100,
                            disabledContentColor = White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share quote", modifier = Modifier.padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}

