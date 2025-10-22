package com.example.proyectoapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectoapp.navigation.AppScreens.*
import com.example.proyectoapp.screens.CourseScreen
import com.example.proyectoapp.screens.HomeScreen
import com.example.proyectoapp.screens.LoginScreen
import com.example.proyectoapp.screens.StudentScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LoginScreen.route) {
        composable(route = LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route = HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(route = StudentScreen.route) {
            StudentScreen(navController)
        }

        composable(route = CourseScreen.route) {
            CourseScreen(navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        NavigationBarItem(
            selected = currentRoute == HomeScreen.route,
            onClick = { navController.navigate(HomeScreen.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentRoute == StudentScreen.route,
            onClick = { navController.navigate(StudentScreen.route) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Estudiantes") },
            label = { Text("Estudiantes") }
        )
        NavigationBarItem(
            selected = currentRoute == CourseScreen.route,
            onClick = { navController.navigate(CourseScreen.route) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Cursos") },
            label = { Text("Cursos") }
        )
    }
}