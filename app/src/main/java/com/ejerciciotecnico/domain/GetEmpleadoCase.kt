package com.ejerciciotecnico.domain

import com.ejerciciotecnico.data.EmpleadoRepositorio
import com.ejerciciotecnico.data.model.EmpleadoModel

class GetEmpleadoCase() {
    private val repositorio = EmpleadoRepositorio()

    suspend operator fun invoke():List<EmpleadoModel>?{
        return repositorio.getEmpleado()
    }


}