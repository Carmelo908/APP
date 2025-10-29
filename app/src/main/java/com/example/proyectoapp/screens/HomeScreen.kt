package com.example.proyectoapp.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.proyectoapp.data.storage.UserPreferences
import com.example.proyectoapp.data.network.ApiService
import com.example.proyectoapp.navigation.AppScreens
import com.example.proyectoapp.ui.layouts.AppLayout
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    AppLayout(navController, "EduTrack") { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Inicio")

            Button(onClick = {
                scope.launch {
                    logout(context)
                    navController.navigate(AppScreens.LoginScreen.route)
                }
            }) {
                Text(text = "Cerrar sesion")
            }
        }
    }
}

suspend fun logout(context: Context) {
    val api = ApiService.create()
    val dataStore = UserPreferences(context)
    val token = dataStore.token.firstOrNull()
    api.logoutUser(token)
}