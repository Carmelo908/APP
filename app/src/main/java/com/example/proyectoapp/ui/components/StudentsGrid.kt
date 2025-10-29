package com.example.proyectoapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectoapp.data.model.Student


@Composable
fun StudentsGrid(students: List<Student>, innerPadding: PaddingValues) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(4),

        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(innerPadding).fillMaxWidth(0.9f)
    ) {
        items(items = students) { student ->
            Text(student.name)
        }
    }
}