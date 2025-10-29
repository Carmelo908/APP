package com.example.proyectoapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoapp.R
import com.example.proyectoapp.data.storage.UserPreferences
import com.example.proyectoapp.data.model.LoginRequest
import com.example.proyectoapp.data.model.LoginResponse
import com.example.proyectoapp.data.network.ApiService
import com.example.proyectoapp.navigation.AppScreens
import com.example.proyectoapp.ui.layouts.LoginLayout
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

data class NoAuthenticated(val message: String)

suspend fun isAuthenticated(context: Context): Boolean {
    val api = ApiService.create()

    val token = UserPreferences(context).token.firstOrNull()

    val response = api.me(token = token)

    return try {
        response.isSuccessful
    } catch (_: Exception) {
        false
    }
}

suspend fun loginController(email: String, password: String): LoginResponse? {
    val api = ApiService.create()

    val loginRequest = LoginRequest(email, password)

    val response = api.login(loginRequest = loginRequest)
    val loginResponse = response.body()
    return loginResponse
}

fun validateLogin(email: String, password: String): Boolean {
    val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
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
        val authenticated = isAuthenticated(context)
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
                text = "Candor...",
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 18.sp,
                color = Color.Gray
            )
            return
        }
    }
    LoginLayout {

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
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "emailIcon"
                )
            },
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
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon"
                )
            },
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
                    if (!validateLogin(email, password)) {
                        Toast.makeText(
                            context, "Datos inválidos",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@launch
                    }
                    val response: LoginResponse? = loginController(email, password)
                    if (response == null) {
                        Toast.makeText(
                            context,
                            "Correo o contraseña incorrecta",
                            Toast.LENGTH_SHORT
                        ).show()
                        password = ""
                        return@launch
                    }
                    val dataStore = UserPreferences(context)
                    val token = "${response.token_type} ${response.access_token}"
                    dataStore.saveToken(token)

                    navController.navigate(AppScreens.HomeScreen.route) {
                        popUpTo(0)
                    }
                }
            },
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .fillMaxWidth(),
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
