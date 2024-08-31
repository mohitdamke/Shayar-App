package com.example.shayariapp.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Colorize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceTheme.colors
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.shayariapp.navigation.Routes
import com.example.shayariapp.ui.theme.ColorsProvider
import com.example.shayariapp.ui.theme.CustomColors
import com.example.shayariapp.ui.theme.defaultColor
import com.example.shayariapp.viewmodel.ColorPreferenceManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorScreen(
    modifier: Modifier = Modifier, navController: NavController,
    onColorChange: (CustomColors) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val context = LocalContext.current
    val colorOptions = listOf(
        CustomColors(
            primary = Color(0xFFd0ba98),
            secondary = Color(0xFFd8caa9),
            background = Color.Black,
            surface = Color.White
        ),
        CustomColors(
            primary = Color(0xFF8d8079),
            secondary = Color(0xFFafa6a1),
            background = Color.Black,
            surface = Color.White
        ),
        CustomColors(
            primary = Color(0xFF986A5A),
            secondary = Color(0xFFac877a),
            background = Color.Black,
            surface = Color.White
        ),
        CustomColors(
            primary = Color(0xFF895A98),
            secondary = Color(0xFFa07aac),
            background = Color.Black,
            surface = Color.White
        ),
        CustomColors(
            primary = Color(0xFF6D9075),
            secondary = Color(0xFF8aa690),
            background = Color.Black,
            surface = Color.White
        ),
        CustomColors(
            primary = Color(0xFF5A8898),
            secondary = Color(0xFF7a9fac),
            background = Color.Black,
            surface = Color.White
        )
    )
    var selectedColors by remember { mutableStateOf(colorOptions[0]) }
    val colorPreferenceManager = remember { ColorPreferenceManager(context) }
    var customColors by remember { mutableStateOf(colorPreferenceManager.getSavedColor()) }
    val handleColorChange = { color: CustomColors ->
        selectedColors = color
        onColorChange(color)
        colorPreferenceManager.saveColor(color)
    }
    LaunchedEffect(key1 = Unit) {
        selectedColors = customColors ?: defaultColor
    }

    ColorsProvider(customColors = customColors ?: defaultColor) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = selectedColors.primary,
                        titleContentColor = selectedColors.surface,
                        actionIconContentColor = selectedColors.surface,
                        navigationIconContentColor = selectedColors.surface,
                        scrolledContainerColor = selectedColors.primary
                    ),
                    title = {
                        Text(
                            text = "Colors Preferences",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 28.sp,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back", modifier = modifier.size(28.dp)

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
                    .background(selectedColors.primary)
                    .padding(padding)
                    .padding(10.dp)
            ) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier.padding(10.dp)
                ) {
                    items(colorOptions) { colorOption ->
                        ColorCard(
                            color = colorOption.primary,
                            onClick = { handleColorChange(colorOption) },
                            isSelected = selectedColors == colorOption
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorCard(
    color: Color, modifier: Modifier = Modifier, onClick: () -> Unit, isSelected: Boolean
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) Color.White else Color.Transparent,
                shape = MaterialTheme.shapes.large
            )
        // Adjust the size as needed
        , colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(10.dp), onClick = onClick
    ) {
        Box(
            modifier = modifier
                .background(color = color)
                .height(350.dp)

        )
    }
}
