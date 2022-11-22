package com.ejerciciotecnico.data.model

import java.util.*

data class EmpleadoModel (
    val createdAt : Int,
    val fullName : String,
    val avatar : String,
    val latitude :Double,
    val longitude : Double,
    val utcDate : String,
    val dateOfBirth : String,
    val id : String
)