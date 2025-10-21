package com.example.proyectoapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, innerPadding: PaddingValues) {
    Text(text = "Inicio", modifier = Modifier.padding(innerPadding))
}