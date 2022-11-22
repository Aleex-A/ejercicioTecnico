package com.ejerciciotecnico.data

import com.ejerciciotecnico.data.model.EmpleadoModel
import com.ejerciciotecnico.data.model.EmpleadoModelPost
import com.ejerciciotecnico.data.model.EmpleadoProvider
import com.ejerciciotecnico.data.network.EmpleadoService

class EmpleadoRepositorio {
    private val api = EmpleadoService()

    suspend fun getEmpleado():List<EmpleadoModel> {
       val response = api.getEmpleados()
        EmpleadoProvider.empleados = response
        return response
    }

    suspend fun getEmpleadoPorId(id: String): EmpleadoModel {
        val response = api.getEmpleadosPorId(id)
        EmpleadoProvider.empleado = response
        return response
    }

    suspend fun postEmpleado(empleadoModelPost: EmpleadoModelPost): EmpleadoModel {
        val response = api.postEmpleados(empleadoModelPost)
        EmpleadoProvider.empleado = response
        return response
    }
    suspend fun editEmpleado(id:String,empleadoModelPost: EmpleadoModelPost): EmpleadoModel {
        val response = api.editEmpleados(id,empleadoModelPost)
        EmpleadoProvider.empleado = response
        return response
    }

    suspend fun deleteEmpleado(id: String): EmpleadoModel {
        val response = api.deleteEmpleados(id)
        EmpleadoProvider.empleado = response
        return response
    }

}