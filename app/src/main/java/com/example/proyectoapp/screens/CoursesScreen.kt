package com.example.proyectoapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.proyectoapp.data.UserPreferences
import com.example.proyectoapp.data.model.User
import com.example.proyectoapp.data.network.ApiService
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun CoursesScreen(navController: NavController) {
    Scaffold { innerPadding ->
        var user by rememberSaveable { mutableStateOf<User?>(null) }
        val context = LocalContext.current
        val dataStore = remember { UserPreferences(context) }

        LaunchedEffect(Unit) {
            val api = ApiService.create()
            val tokenValue = dataStore.token.firstOrNull() ?: ""
            val token = "Bearer $tokenValue"
            user = api.me(token = token)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding)
        ) {
            Text(text = "nombre del usuario: ${user?.name ?: "Cargando..."}")
            Text(text = "Email del usuario: ${user?.email ?: "Cargando..."}")
        }
    }
}