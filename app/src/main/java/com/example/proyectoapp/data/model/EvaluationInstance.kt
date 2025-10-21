package com.example.proyectoapp.data.model

import java.util.Date
import java.util.HashMap

enum class instances_types {
    homework,
    attendance,
    project,
    class_participation,
    practical_work,
    class_activity,
    others
}



data class EvaluationInstance(var type: instances_types, var instanceDate: Date, var qualifications: Map<Int, String>)