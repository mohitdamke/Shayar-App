package com.example.shayariapp.presentation.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.drawToBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shayariapp.R
import com.example.shayariapp.ui.theme.LocalCustomColors
import com.example.shayariapp.viewmodel.ShayariViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShayariDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    shayariId: String,
) {
    val context = LocalContext.current
    val captureView = LocalView.current
    val scope = rememberCoroutineScope()
    val viewModel: ShayariViewModel = hiltViewModel()
    val shayari by viewModel.getShayariById(shayariId).collectAsState(initial = null)
    val clipboardManager: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    var fullScreenshot by remember { mutableStateOf<Bitmap?>(null) }
    var isIconsVisible by remember { mutableStateOf(true) }
    val customColors = LocalCustomColors.current



    shayari?.let { quote ->
        ShayariDetailItem(
            quote = quote.text,
            isBookmarked = quote.isBookmarked,
            onSaveClicked = { viewModel.saveOrUnsaveQuote(quote, context) },
            onCopyClicked = {
                val clip = ClipData.newPlainText(
                    "Quote", quote.text
                )
                clipboardManager.setPrimaryClip(clip)
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            },
            onShareClicked = {
                scope.launch {
                    Toast.makeText(context, "Sharing...", Toast.LENGTH_SHORT).show()
                    isIconsVisible = false
                    delay(500)
                    fullScreenshot = captureView.drawToBitmap(Bitmap.Config.ARGB_8888)
                    isIconsVisible = true
                    if (fullScreenshot != null) {
                        shareImageContent(fullScreenshot!!, context)
                    }
                }
            }, isIconsVisible = isIconsVisible,
            cardColor = customColors.secondary,
            titleColor = customColors.surface,
            iconColor = customColors.surface
        )
    } ?: run {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Loading quote details...", fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(key1 = shayari) {
        Log.e("Data Fetch TAG", "Quote loaded with ID: $shayariId")
    }
}


@Composable
private fun ShayariDetailItem(
    modifier: Modifier = Modifier,
    quote: String,
    isBookmarked: Boolean,
    onSaveClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onCopyClicked: () -> Unit,
    isIconsVisible: Boolean,
    cardColor: Color,
    titleColor: Color,
    iconColor: Color,
) {
    Scaffold { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(cardColor)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = modifier
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = quote,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            color = titleColor
                        )
                        Spacer(modifier = modifier.padding(top = 10.dp))
                        Card(
                            modifier = modifier
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(White)
                        ) {
                            Row(
                                modifier = modifier
                                    .padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo),
                                    contentDescription = "logo",
                                    modifier = modifier
                                        .size(26.dp)
                                        .clip(
                                            CircleShape
                                        )
                                )
                                Spacer(modifier = modifier.padding(start = 6.dp))
                                Text(
                                    text = "shayar.app",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    color = Black
                                )
                            }
                        }

                    }
                    if (isIconsVisible) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 100.dp),
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Spacer(modifier = modifier.padding(start = 40.dp))

                            Icon(imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                modifier = modifier
                                    .clickable {
                                        onSaveClicked()
                                    }
                                    .size(40.dp),
                                tint = iconColor)


                            Icon(imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy to clipboard",
                                modifier = modifier
                                    .clickable {
                                        onCopyClicked()
                                    }
                                    .size(40.dp),
                                tint = iconColor)


                            Icon(imageVector = Icons.Filled.IosShare,
                                contentDescription = "Share quote",
                                modifier = modifier
                                    .clickable {
                                        onShareClicked()
                                    }
                                    .size(40.dp),
                                tint = iconColor

                            )
                        }
                    }

                }
            }
        }
    }
}


// Function to share the captured image
private fun shareImageContent(image: Bitmap, context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "image/png"
        putExtra(
            Intent.EXTRA_STREAM, Uri.parse(
                MediaStore.Images.Media.insertImage(
                    context.contentResolver, image, "Shayari", "Shayari Wallpaper"
                )
            )
        )
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Shayari"))
}


