package com.example.shayariapp.sidebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


@Composable
fun SideMenu(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerMinOffsetAbs = -with(LocalDensity.current) { (-360).dp.toPx() }.dp
    val width = 300.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        modifier = modifier,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .width(width)
                    .fillMaxHeight()
                    .clickable(
                        enabled = true,
                        onClickLabel = null,
                        role = null,
                        onClick = {},
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    )
            ) {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
            }
        }
    ) {
        Box(
            modifier = Modifier
                .offset(x = (drawerState.offset.value.dp + drawerMinOffsetAbs) * (width / drawerMinOffsetAbs))
        ) {
            Text("Screen title", modifier = Modifier.padding(16.dp))
        }
    }
}