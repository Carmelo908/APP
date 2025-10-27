package com.example.proyectoapp.data.model

data class Course(val id: Int, val year: String, val division: String, val orientation: String)


data class CoursePostResponse(val course: Course, val message: String)

data class CoursePostRequest(val year: String, val division: String, val orientation: String)