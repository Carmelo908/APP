package com.example.proyectoapp.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.proyectoapp.data.storage.UserPreferences
import com.example.proyectoapp.data.model.Course
import com.example.proyectoapp.data.network.ApiService
import com.example.proyectoapp.ui.components.ButtonAdd
import com.example.proyectoapp.ui.layouts.AppLayout
import kotlinx.coroutines.flow.firstOrNull

suspend fun getCourses(context: Context): List<Course>? {
    val api = ApiService.create()

    val dataStore = UserPreferences(context)
    val token = dataStore.token.firstOrNull()

    val courses = api.getCourses(token).body()

    return courses
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(navController: NavController) {
    var isLoading by remember { mutableStateOf(true) }
    var courses by remember { mutableStateOf<List<Course>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            val response = getCourses(context)
            courses = response ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    AppLayout(navController, title = "EduTrack - Cursos") { innerPadding ->
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
                Text("Cargando Cursos...")
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    items(courses) { course ->
                        Card(
                            onClick = {
                                navController.navigate(route = "course_screen/${course.id}")
                            },
                            modifier = Modifier
                                .height(100.dp)
                                .padding(5.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {
                            Row(modifier = Modifier.padding(10.dp)) {
                                Text(text = "${course.year}° ${course.division}°")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier
                    .height(16.dp)
                    .padding(innerPadding))

                ButtonAdd(modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .align(Alignment.BottomEnd))
                {

                }
            }
        }
    }
}