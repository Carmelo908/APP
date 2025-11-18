package com.example.proyectoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectoapp.screens.CourseScreen
import com.example.proyectoapp.screens.CourseListScreen
import com.example.proyectoapp.screens.HomeScreen
import com.example.proyectoapp.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.resolve("true")) {
        composable(
            route = AppScreens.LoginScreen.get(),
            arguments = listOf(navArgument("checkAuth") { type = NavType.BoolType })
        ) { backStackEntry ->
            val checkAuth = backStackEntry.arguments?.getBoolean("checkAuth") ?: true
            LoginScreen(navController, checkAuth)
        }
        composable(route = AppScreens.HomeScreen.get()) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.CoursesScreen.get()) {
            CourseListScreen(navController)
        }
        composable(
            route = AppScreens.CourseScreen.get(),
            arguments = listOf(navArgument("courseID") { type = NavType.IntType }, navArgument("courseName") { type = NavType.StringType })
        ) { backStackEntry ->
            val courseID = backStackEntry.arguments?.getInt("courseID") ?: 1
            val courseName = backStackEntry.arguments?.getString("courseName") ?: "Course"
            CourseScreen(navController, courseID, courseName)
        }
    }
}

