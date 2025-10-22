package com.example.proyectoapp.data.network

import com.example.proyectoapp.data.model.LoginRequest
import com.example.proyectoapp.data.model.LoginResponse
import com.example.proyectoapp.data.model.StudentsResponse
import com.example.proyectoapp.data.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    companion object {
        private const val BASE_URL = "http://45.234.32.91:8000/"

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

    @GET("students")
    suspend fun getAllStudents(@Header("Authorization") token: String?): Response<StudentsResponse>

    @POST("/logout")
    suspend fun logoutUser(@Header("Authorization") token: String?)

}