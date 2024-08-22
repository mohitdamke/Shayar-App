package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ShayariDetail(
    modifier: Modifier = Modifier,
    navController: NavController,
    quote: String,
    author: String
) {
Text(text = "quote")
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun EmotionfDetailCards(
    modifier: Modifier = Modifier,
    text: String = "उजाले अपनी यादों के हमारे साथ रहने दो \n" +
            "\n" +
            "न जाने किस गली में ज़िंदगी की शाम हो जाए ",
    cardClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            onClick = { cardClick() }, colors = CardDefaults.cardColors(White)
        ) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = modifier.padding(10.dp)
            )
            Spacer(modifier = modifier.padding(top = 10.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = null,
                    modifier = modifier
                        .clickable { }
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.CenterVertically)
                        .size(30.dp)
                )

                Icon(imageVector = Icons.Default.Share, contentDescription = null,
                    modifier = modifier
                        .clickable { }
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.CenterVertically)
                        .size(30.dp))

                Icon(imageVector = Icons.Default.Star, contentDescription = null,
                    modifier = modifier
                        .clickable { }
                        .padding(start = 20.dp, end = 20.dp)
                        .align(Alignment.CenterVertically)
                        .size(30.dp))
            }

            Spacer(modifier = modifier.padding(top = 10.dp))

        }
    }
}
