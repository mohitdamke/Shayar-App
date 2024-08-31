package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceTheme.colors
import androidx.navigation.NavController
import com.example.shayariapp.navigation.Routes
import com.example.shayariapp.ui.theme.LocalCustomColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyScreen(modifier: Modifier = Modifier, navController: NavController) {

    val customColors = LocalCustomColors.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
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
                        text = "Privacy Policy",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Medium, fontSize = 28.sp,
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(customColors.primary)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = """Your privacy is important to us. This privacy statement explains the personal data our app collects, how it is used, and how it is shared.
                    1. **Data Collection**: We collect personal data such as your name, email address, and usage data to improve our services.
                    2. **Data Usage**: The collected data is used to enhance the user experience, provide customer support, and send updates about our app.
                    3. **Data Sharing**: We do not sell or rent your personal data to third parties. We may share your data with trusted partners to help us improve our services.
                    4. **Security**: We implement appropriate security measures to protect your data from unauthorized access, alteration, or destruction.
                    5. **Changes**: We may update this privacy policy from time to time. Changes will be posted on this page with an updated revision date.
                    6. **Contact**: If you have any questions or concerns about our privacy policy, please contact us at [support@example.com].
                """,
                fontStyle = FontStyle.Normal,
                color = customColors.surface,
                fontSize = 22.sp, textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}