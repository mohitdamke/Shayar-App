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
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IosShare
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
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
import com.example.shayariapp.ui.theme.LocalCustomColors
import com.example.shayariapp.viewmodel.ShayariViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShayariListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    genre: String,
) {
    val viewModel: ShayariViewModel = hiltViewModel()
    val shayariGenre by viewModel.getShayariByGenre(genre).collectAsState(initial = emptyList())
    val shayari by viewModel.shayariList.collectAsState(initial = emptyList())
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    val clipboardManager: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val customColors = LocalCustomColors.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = customColors.primary,
                    titleContentColor = customColors.surface,
                    actionIconContentColor = customColors.surface,
                    navigationIconContentColor = customColors.surface,
                    scrolledContainerColor = customColors.primary
                ),
                title = {
                    Text(
                        text = "$genre Shayari",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.ExtraBold, fontSize = 28.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = modifier.size(28.dp)

                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Routes.Saved.route) }) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Localized description",
                            modifier = modifier.size(28.dp)
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
                .padding(padding)
                .background(customColors.primary),
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
                        ShayariItem(shayari = shayari,
                            onClick = {
                                // Navigate to detail screen, adjust route if using unique ID
                                navController.navigate(
                                    Routes.ShayariDetail.route.replace(
                                        "{shayariId}", shayari.id.toString()
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
                                        shareIntent, "Share via"
                                    )
                                )


                            },
                            onCopyClicked = {
                                val clip = ClipData.newPlainText(
                                    "Quote", shayari.text
                                )
                                clipboardManager.setPrimaryClip(clip)
                                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT)
                                    .show()
                            },
                            cardColor = customColors.secondary,
                            titleColor = customColors.surface,
                            iconColor = customColors.surface
                        )
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
    cardColor: Color,
    titleColor: Color,
    iconColor: Color,


    ) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(cardColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = shayari.text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = titleColor
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Spacer(modifier = Modifier.padding(start = 80.dp))

                Icon(imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onSaveClicked()
                        }
                        .size(30.dp), tint = iconColor
                )


                Icon(imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy to clipboard",
                    modifier = Modifier
                        .clickable {
                            onCopyClicked()
                        }
                        .size(30.dp), tint = iconColor
                )


                Icon(imageVector = Icons.Filled.IosShare,
                    contentDescription = "Share quote",
                    modifier = Modifier
                        .clickable {
                            onShareClicked()
                        }
                        .size(30.dp), tint = iconColor

                )
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}





