package com.example.proyectoapp.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("login_screen")

    object HomeScreen: AppScreens("home_screen")

    object CoursesScreen: AppScreens("courses_screen")

    object CourseScreen: AppScreens("course_screen/{course_id}")
}
