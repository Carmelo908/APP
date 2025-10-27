package com.example.proyectoapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonAdd(modifier: Modifier, onClick: () -> Unit) {
    Button (
        modifier = modifier,
        shape = RoundedCornerShape(10), // botón redondeado
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174071)),
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Agregar",
            tint = Color.White,
        )
    }
}