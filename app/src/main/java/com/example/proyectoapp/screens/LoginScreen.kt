package com.example.proyectoapp.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import com.example.proyectoapp.AppScreens
import com.example.proyectoapp.R
import com.example.proyectoapp.data.UserPreferences
import com.example.proyectoapp.data.model.LoginRequest
import com.example.proyectoapp.data.model.LoginResponse
import com.example.proyectoapp.data.network.ApiService
import kotlinx.coroutines.launch

suspend fun LoginController(navController: NavController, email: String, password: String): LoginResponse
{
    val api = ApiService.create()

    val loginRequest: LoginRequest = LoginRequest(email, password)

    val response: LoginResponse = api.login(loginRequest = loginRequest)

    return response
}


@Composable
fun LoginScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var email: String by rememberSaveable() { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var token: String by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "EduTrack",
                color = Color.Black,
                modifier = Modifier.padding(bottom = 30.dp),
                fontSize = 3.em
            )
            Image(
                painter = painterResource(id = R.drawable.logo_escuela),
                contentDescription = "Logo de la Escuela Técnica N°2"
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("Correo electronico") },
                modifier = Modifier.padding(20.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                placeholder = { Text("Contraseña") },
                modifier = Modifier.padding(20.dp)
            )
            Button(
                onClick = {
                    scope.launch {
                        val response: LoginResponse = LoginController(navController, email, password)

                        val dataStore = UserPreferences(context)

                        dataStore.saveToken(token = response.token_type + " " + response.access_token)

                        navController.navigate(AppScreens.CoursesScreen.route)
                    }
                },
                content = { Text("Iniciar sesion") }
            )

            Text(text = token)
        }
    }
}