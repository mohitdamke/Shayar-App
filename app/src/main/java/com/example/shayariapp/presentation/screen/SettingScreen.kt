package com.example.shayariapp.presentation.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shayariapp.navigation.Routes
import com.example.shayariapp.ui.theme.CustomColors
import com.example.shayariapp.ui.theme.LocalCustomColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val context = LocalContext.current
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
                            text = "Settings",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 28.sp,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back", modifier = modifier.size(28.dp)

                            )
                        }

                    }, actions = {
                        IconButton(onClick = { navController.navigate(Routes.Color.route) }) {
                            Icon(
                                imageVector = Icons.Filled.Colorize,
                                contentDescription = "Color", modifier = modifier.size(28.dp)

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
                    .background(customColors.primary)
                    .padding(padding)
                    .padding(10.dp),
            ) {
                Spacer(modifier = modifier.padding(top = 10.dp))
                SettingItem(
                    onCLick = { navController.navigate(Routes.Home.route) },
                    icon = Icons.Default.Home,
                    text = "Home",
                    description = "Back to Home",
                    iconColor = customColors.surface,
                    titleColor = customColors.surface,
                    despColor = customColors.surface,
                    cardColor = customColors.secondary
                )
                Spacer(modifier = modifier.padding(top = 20.dp))
                SettingItem(
                    onCLick = { navController.navigate(Routes.Saved.route) },
                    icon = Icons.Default.Bookmark,
                    text = "Bookmark",
                    description = "View your saved quotes",
                    iconColor = customColors.surface,
                    titleColor = customColors.surface,
                    despColor = customColors.surface,
                    cardColor = customColors.secondary

                )
                Spacer(modifier = modifier.padding(top = 20.dp))
                SettingItem(
                    onCLick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, Typography.quote)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                    },
                    icon = Icons.Default.Share,
                    text = "Share with friends",
                    description = "Share the app with your friends",
                    iconColor = customColors.surface,
                    titleColor = customColors.surface,
                    despColor = customColors.surface,
                    cardColor = customColors.secondary
                )
                Spacer(modifier = modifier.padding(top = 20.dp))
                SettingItem(
                    onCLick = { navController.navigate(Routes.Privacy.route) },
                    icon = Icons.Default.PrivacyTip,
                    text = "Privacy & Policy",
                    description = "Review our privacy policy",
                    iconColor = customColors.surface,
                    titleColor = customColors.surface,
                    despColor = customColors.surface,
                    cardColor = customColors.secondary
                )

            }
        }
    }




@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    description: String,
    onCLick: () -> Unit,
    iconColor: Color,
    titleColor: Color,
    despColor: Color,
    cardColor: Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(cardColor),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .clickable {
                    onCLick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = modifier.size(30.dp), tint = iconColor
            )
            Spacer(modifier = modifier.padding(start = 14.dp))
            Column(
                modifier = modifier.padding(top = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = modifier.padding(top = 4.dp))

                Text(
                    text = text,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = titleColor
                )
                Spacer(modifier = modifier.padding(top = 4.dp))

                Text(
                    text = description,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    color = despColor
                )
                Spacer(modifier = modifier.padding(top = 4.dp))

            }
        }
    }
}


