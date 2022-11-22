package com.ejerciciotecnico.domain

import com.ejerciciotecnico.data.EmpleadoRepositorio
import com.ejerciciotecnico.data.model.EmpleadoModel

class DeleteEmpleadoCase(private val id: String) {

    private val repositorio = EmpleadoRepositorio()


   suspend operator fun invoke(): EmpleadoModel?{
      return repositorio.deleteEmpleado(id)
    }


}