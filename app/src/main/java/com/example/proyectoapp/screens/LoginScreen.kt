package com.example.proyectoapp.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoapp.R
import com.example.proyectoapp.data.UserPreferences
import com.example.proyectoapp.data.model.LoginRequest
import com.example.proyectoapp.data.model.LoginResponse
import com.example.proyectoapp.data.network.ApiService
import com.example.proyectoapp.navigation.AppScreens
import com.example.proyectoapp.navigation.BottomBar
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

data class NoAuthenticated(val message: String)

suspend fun IsAuthenticated(context: Context): Boolean
{
    val api = ApiService.create()

    val token = UserPreferences(context).token.firstOrNull()

    val response = api.me(token = token)

    return try {
        response.isSuccessful
    } catch (e: Exception) {
        false
    }
}

suspend fun LoginController( email: String, password: String): LoginResponse? {
    val api = ApiService.create()

    val loginRequest: LoginRequest = LoginRequest(email, password)

    val response = api.login(loginRequest = loginRequest)
    val loginResponse = response.body()
    return loginResponse
}

@Composable
fun LoginScreen(navController: NavController) {
    var email: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var checkingAuth by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val authenticated = IsAuthenticated(context)
        if (authenticated) {
            navController.navigate(AppScreens.HomeScreen.route) {
                popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
            }
        } else {
            checkingAuth = false
        }
    }

    if (checkingAuth) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFF6200EE),
                strokeWidth = 4.dp
            )
            Text(
                text = "Cargando...",
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "EduTrack",
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 30.dp),
                    fontSize = 30.sp
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
                    modifier = Modifier.padding(20.dp),
                    shape = RoundedCornerShape(20.dp)
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    placeholder = { Text("Contraseña") },
                    modifier = Modifier.padding(20.dp),
                    shape = RoundedCornerShape(20.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                Button(
                    onClick = {
                        scope.launch {
                            val response: LoginResponse? = LoginController(email, password)
                            if (response != null) {
                                val dataStore = UserPreferences(context)
                                dataStore.saveToken("${response.token_type} ${response.access_token}")

                                navController.navigate(AppScreens.HomeScreen.route) {
                                    popUpTo(0)
                                }
                            }
                        }
                    },
                    content = { Text("Iniciar sesión") }
                )
            }
        }
    }
}
