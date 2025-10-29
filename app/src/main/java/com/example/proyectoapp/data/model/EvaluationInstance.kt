package com.example.proyectoapp.data.model

import java.util.Date
import java.util.HashMap

enum class InstancesTypes {
    homework,
    attendance,
    project,
    class_participation,
    practical_work,
    class_activity,
    others
}


data class EvaluationInstance(
    var type: InstancesTypes,
    var instanceDate: Date,
    var qualifications: HashMap<Int, String>
)