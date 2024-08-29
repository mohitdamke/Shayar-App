package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Privacy(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Privacy Policy") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = """
                    Your privacy is important to us. This privacy statement explains the personal data our app collects, how it is used, and how it is shared.
                    
                    1. **Data Collection**: We collect personal data such as your name, email address, and usage data to improve our services.
                    2. **Data Usage**: The collected data is used to enhance the user experience, provide customer support, and send updates about our app.
                    3. **Data Sharing**: We do not sell or rent your personal data to third parties. We may share your data with trusted partners to help us improve our services.
                    4. **Security**: We implement appropriate security measures to protect your data from unauthorized access, alteration, or destruction.
                    5. **Changes**: We may update this privacy policy from time to time. Changes will be posted on this page with an updated revision date.
                    6. **Contact**: If you have any questions or concerns about our privacy policy, please contact us at [support@example.com].
                """,
                fontStyle = FontStyle.Normal, fontSize = 22.sp, modifier = Modifier.padding(16.dp)
            )
        }
    }
}