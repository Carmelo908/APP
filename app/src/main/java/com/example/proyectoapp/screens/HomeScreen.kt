package com.example.proyectoapp.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.proyectoapp.data.UserPreferences
import com.example.proyectoapp.data.network.ApiService
import com.example.proyectoapp.navigation.AppScreens
import com.example.proyectoapp.navigation.BottomBar
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Scaffold (
        bottomBar = {
            BottomBar(navController)
        },
        topBar = {
            TopAppBar(
                title = { Text("EduTrack") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Column {
            Text(text = "Inicio", modifier = Modifier.padding(innerPadding))

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

suspend fun logout(context: Context)
{
    val api = ApiService.create()

    val dataStore = UserPreferences(context)
    val token = dataStore.token.firstOrNull()
    api.logoutUser(token)
}