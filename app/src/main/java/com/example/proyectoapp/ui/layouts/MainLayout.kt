package com.example.proyectoapp.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.proyectoapp.ui.components.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(
    navController: NavController,
    title: String = "EduTrack",
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = { BottomBar(navController) },
        modifier = Modifier.background(Color.White)
    ) { innerPadding ->
        content(innerPadding)
    }
}
