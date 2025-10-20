package com.example.proyectoapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun CoursesScreen(navController: NavController, ) {
    Scaffold { innerPadding ->
        Toast.makeText(LocalContext.current, "Se ha iniciado sesión con éxito", Toast.LENGTH_SHORT).show()
        Button(onClick = {  }, modifier = Modifier.padding(innerPadding)) { }
    }
}