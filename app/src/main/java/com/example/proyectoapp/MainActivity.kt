package com.example.proyectoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoapp.ui.theme.ProyectoAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoAPPTheme {
                    AppNavigation()
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email: String by rememberSaveable() { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }

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
                text = "Asistente para docentes",
                color = Color.White,
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
                onClick = { navController.navigate(AppScreens.CoursesScreen.route) },
                content = { Text("Iniciar sesion") })
        }
    }
}
@Composable
fun CoursesScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Text(text = "Bienvenido, profesor", modifier = modifier.padding(innerPadding))
    }
}

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("login_screen")
    object CoursesScreen: AppScreens("courses_screen")
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController, modifier = modifier)
        }
        composable(route = AppScreens.CoursesScreen.route) {
            CoursesScreen(navController, modifier = modifier)
        }
    }
}