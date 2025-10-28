package com.example.proyectoapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoapp.R
import com.example.proyectoapp.data.UserPreferences
import com.example.proyectoapp.data.model.LoginRequest
import com.example.proyectoapp.data.model.LoginResponse
import com.example.proyectoapp.data.network.ApiService
import com.example.proyectoapp.navigation.AppScreens
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation

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

fun ValidateLogin(email: String, password: String): Boolean {
    val emailRegex: Regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    return emailRegex.matches(email) && !password.isEmpty()
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
            return
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize().imePadding(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
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

            Spacer(modifier = Modifier.padding(30.dp))

            OutlinedTextField(
                value = email,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
                onValueChange = {
                    email = it
                },
                singleLine = true,
                placeholder = { Text(text = "Correo electrónico") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6200EE),
                    focusedLabelColor = Color(0xFF6200EE),
                )
            )

            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                value = password,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon") },
                onValueChange = {
                    password = it
                },
                placeholder = { Text(text = "Contraseña") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6200EE),
                    focusedLabelColor = Color(0xFF6200EE),
                )
            )

            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Button(
                onClick = {
                    scope.launch {
                        if (!ValidateLogin(email, password))
                        {
                            Toast.makeText(context, "Datos inválidos", Toast.LENGTH_SHORT).show()
                            return@launch
                        }
                        val response: LoginResponse? = LoginController(email, password)
                        if (response == null) {
                            Toast.makeText(context, "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                            password = ""
                            return@launch
                        }
                        val dataStore = UserPreferences(context)
                        dataStore.saveToken("${response.token_type} ${response.access_token}")

                        navController.navigate(AppScreens.HomeScreen.route) {
                            popUpTo(0)
                        }
                    }
                },
                modifier = Modifier.padding(horizontal = 26.dp).fillMaxWidth(),
                content = { Text("Iniciar sesión") },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = Color(0xFF6200EE),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            )
        }
    }

}