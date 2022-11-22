package com.ejerciciotecnico.data.model

import android.graphics.ColorSpace.Model
import com.ejerciciotecnico.data.model.EmpleadoModel
import java.text.SimpleDateFormat

class EmpleadoProvider {
        companion object {
            var empleados:List<EmpleadoModel> = emptyList()
            var empleado: EmpleadoModel = EmpleadoModel(0,"","",0.0,0.0,"","","")
        }
    }