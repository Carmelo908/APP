package com.example.proyectoapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoapp.data.UserPreferences
import com.example.proyectoapp.data.model.Student
import com.example.proyectoapp.data.network.ApiService
import com.example.proyectoapp.navigation.BottomBar
import com.example.proyectoapp.ui.layouts.AppLayout
import com.example.proyectoapp.ui.components.StudentsGrid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(navController: NavController, course_id: Int) {
    var isLoading by remember { mutableStateOf(true) }
    var students by remember { mutableStateOf(listOf<Student>()) }

    val context = LocalContext.current

    LaunchedEffect(course_id) {
        isLoading = true
        try {
            val dataStore = UserPreferences(context)
            val token = dataStore.token.firstOrNull()

            val api = ApiService.create()
            val result = withContext(Dispatchers.IO) {
                api.getStudentsByCourse(token, course_id)
            }

            students = result.body() ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            students = emptyList()
        } finally {
            isLoading = false
        }
    }

    AppLayout(navController, title = "EduTrack - 1° 1°") { innerPadding ->
        if (isLoading) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = Color(0xFF174071),
                    strokeWidth = 4.dp
                )
                Text("Cargando alumnos...", color = Color.Red)
            }
        } else {
            if(students.isEmpty()) {
                Text("No hay alumnos en el curso ta ta",modifier = Modifier.padding(innerPadding))
                return@AppLayout
            }

            StudentsGrid(students, innerPadding)
        }
    }
}