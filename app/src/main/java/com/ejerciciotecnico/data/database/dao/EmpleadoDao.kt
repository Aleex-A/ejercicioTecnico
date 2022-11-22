package com.ejerciciotecnico.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ejerciciotecnico.data.database.entities.EmpleadoEntity

@Dao
interface EmpleadoDao {

    @Query("SELECT * FROM empleados_Table ORDER BY id ASC")
    suspend fun getEmpleados():List<EmpleadoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(empleado:List<EmpleadoEntity>)

}