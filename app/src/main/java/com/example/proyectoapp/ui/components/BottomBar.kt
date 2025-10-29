package com.example.proyectoapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectoapp.navigation.AppScreens

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.Companion.White,
        contentColor = Color.Companion.Black
    ) {
        NavigationBarItem(
            selected = currentRoute == AppScreens.HomeScreen.route,
            onClick = { navController.navigate(AppScreens.HomeScreen.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentRoute == AppScreens.CoursesScreen.route,
            onClick = { navController.navigate(AppScreens.CoursesScreen.route) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Cursos") },
            label = { Text("Cursos") }
        )
    }
}