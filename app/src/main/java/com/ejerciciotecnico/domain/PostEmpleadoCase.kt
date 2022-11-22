package com.ejerciciotecnico.domain

import com.ejerciciotecnico.data.EmpleadoRepositorio
import com.ejerciciotecnico.data.model.EmpleadoModel
import com.ejerciciotecnico.data.model.EmpleadoModelPost

class PostEmpleadoCase(private val empleadoModelPost: EmpleadoModelPost) {


    private val repositorio = EmpleadoRepositorio()


    suspend operator fun invoke(): EmpleadoModel?{
        return repositorio.postEmpleado(empleadoModelPost)
    }


}