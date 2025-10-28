package com.example.proyectoapp.data.model

data class StudentsResponse(
    val students: List<Student>,
    val message: String
)

data class Student(
    val id: Int,
    val name: String,
    val surname: String,
    val course_id: Int,
    val created_at: String,
    val updated_at: String
)