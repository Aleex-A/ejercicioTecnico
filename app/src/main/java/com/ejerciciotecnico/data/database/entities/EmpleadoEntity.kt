package com.ejerciciotecnico.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empleados_Table")
class EmpleadoEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : String,
    @ColumnInfo(name = "createdAt") val createdAt : Int,
    @ColumnInfo(name = "fullName") val fullName : String,
    @ColumnInfo(name = "avatar") val avatar : String,
    @ColumnInfo(name = "latitude") val latitude :Double,
    @ColumnInfo(name = "longitude") val longitude : Double,
    @ColumnInfo(name = "utcDate") val utcDate : String,
    @ColumnInfo(name = "dateOfBirth") val dateOfBirth : String
    )