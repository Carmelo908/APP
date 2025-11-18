package com.example.proyectoapp.navigation

class Route(val route: String) {

    fun resolve(arg: String): String {
        val regex = Regex("\\{(.*?)\\}")
        return regex.replace(route, arg)
    }

    fun get(): String = route
}

object AppScreens {
    val LoginScreen = Route("login_screen/{checkAuth}")
    val HomeScreen = Route("home_screen")
    val CoursesScreen = Route("courses_screen")
    val CourseScreen = Route("course_screen/{courseID}/{courseName}")


}