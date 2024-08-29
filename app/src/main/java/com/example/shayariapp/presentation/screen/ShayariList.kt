package com.example.shayariapp.presentation.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shayariapp.data.db.ShayariEntity
import com.example.shayariapp.navigation.Routes
import com.example.shayariapp.ui.theme.Blue100
import com.example.shayariapp.ui.theme.Blue40
import com.example.shayariapp.viewmodel.ShayariViewModel
import kotlin.text.Typography.quote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShayariList(
    modifier: Modifier = Modifier,
    navController: NavController,
    genre: String,
) {
    val viewModel: ShayariViewModel = hiltViewModel()
    val shayariGenre by viewModel.getShayariByGenre(genre).collectAsState(initial = emptyList())
    val shayari by viewModel.quoteList.collectAsState(initial = emptyList())
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    val clipboardManager: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager



    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "$genre Shayari",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive,
                        color = Color.Black, fontSize = 38.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = modifier.size(30.dp)

                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Routes.Saved.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Bookmark,
                            contentDescription = "Localized description",
                            modifier = modifier.size(30.dp)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
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
            if (shayari.isEmpty() || shayariGenre.isEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "No Shayari Found", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                LazyColumn {
                    items(shayariGenre) { shayari ->
                        ShayariItem(
                            shayari = shayari,
                            onClick = {
                                // Navigate to detail screen, adjust route if using unique ID
                                navController.navigate(
                                    Routes.ShayariDetail.route.replace(
                                        "{shayariId}",
                                        shayari.id.toString()
                                    )
                                )
                            },
                            isBookmarked = shayari.isBookmarked,
                            onSaveClicked = { viewModel.saveOrUnsaveQuote(shayari, context) },
                            onShareClicked = {
                                val shareIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, shayari.text)
                                    type = "text/plain"
                                }
                                context.startActivity(
                                    Intent.createChooser(
                                        shareIntent,
                                        "Share via"
                                    )
                                )


                            }, onCopyClicked = {
                                val clip = ClipData.newPlainText(
                                    "Quote",
                                    shayari.text
                                )
                                clipboardManager.setPrimaryClip(clip)
                                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                    .show()
                            })
                    }
                }
            }
        }
    }
}

@Composable
private fun ShayariItem(
    shayari: ShayariEntity,
    onClick: () -> Unit,
    isBookmarked: Boolean,
    onSaveClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onCopyClicked: () -> Unit,

    ) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)
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
                text = shayari.text,
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
                        contentColor = Color.White,
                        disabledContainerColor = Blue100,
                        disabledContentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else
                            Icons.Default.BookmarkBorder,
                        contentDescription = null, modifier = Modifier.padding(2.dp)
                    )
                }
                Button(
                    onClick = {
                        onCopyClicked()

                    },
                    modifier = Modifier.padding(top = 18.dp),
                    colors = ButtonColors(
                        containerColor = Blue40,
                        contentColor = Color.White,
                        disabledContainerColor = Blue100,
                        disabledContentColor = Color.White
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy to clipboard", modifier = Modifier.padding(2.dp)
                    )
                }
                Button(
                    onClick = {
                        onShareClicked()

                    },
                    modifier = Modifier.padding(top = 18.dp),
                    colors = ButtonColors(
                        containerColor = Blue40,
                        contentColor = Color.White,
                        disabledContainerColor = Blue100,
                        disabledContentColor = Color.White
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





