package com.example.proyectoapp.data.network

import com.example.proyectoapp.data.model.Course
import com.example.proyectoapp.data.model.CoursePostRequest
import com.example.proyectoapp.data.model.CoursePostResponse
import com.example.proyectoapp.data.model.LoginRequest
import com.example.proyectoapp.data.model.LoginResponse
import com.example.proyectoapp.data.model.Student
import com.example.proyectoapp.data.model.StudentsResponse
import com.example.proyectoapp.data.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    companion object {
        private const val BASE_URL = "https://api-asistente-de-notas.ferchudev.com/"

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("me")
    suspend fun me(@Header("Authorization") token: String?): Response<User>

    @GET("courses")
    suspend fun getCourses(@Header("Authorization") token: String?): Response<List<Course>>

    @GET("students")
    suspend fun getAllStudents(@Header("Authorization") token: String?): Response<StudentsResponse>

    @POST("logout")
    suspend fun logoutUser(@Header("Authorization") token: String?)

    @GET(value = "courses/{course_id}/students")
    suspend fun getStudentsByCourse(
        @Header(value = "Authorization") token: String?,
        @Path(value = "course_id") courseId: Int
    ): Response<List<Student>>

    @POST
    suspend fun createCourse(@Header("Authorization") token: String?, @Body course: CoursePostRequest): CoursePostResponse
}