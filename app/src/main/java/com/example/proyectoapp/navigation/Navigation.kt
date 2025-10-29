package com.example.proyectoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectoapp.navigation.AppScreens.CourseScreen
import com.example.proyectoapp.navigation.AppScreens.CoursesScreen
import com.example.proyectoapp.navigation.AppScreens.HomeScreen
import com.example.proyectoapp.navigation.AppScreens.LoginScreen
import com.example.proyectoapp.screens.CourseScreen
import com.example.proyectoapp.screens.CoursesScreen
import com.example.proyectoapp.screens.HomeScreen
import com.example.proyectoapp.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LoginScreen.route) {
        composable(route = LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = CoursesScreen.route) {
            CoursesScreen(navController)
        }
        composable(
            route = CourseScreen.route,
            arguments = listOf(navArgument("courseID") { type = NavType.IntType })
        ) { backStackEntry ->
            val courseID = backStackEntry.arguments?.getInt("courseID") ?: 1
            CourseScreen(navController, courseID)
        }
    }
}

