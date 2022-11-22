package com.ejerciciotecnico.data.network

import com.ejerciciotecnico.core.RetrofitHelper
import com.ejerciciotecnico.data.model.EmpleadoModel
import com.ejerciciotecnico.data.model.EmpleadoModelPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class EmpleadoService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getEmpleados():List<EmpleadoModel>{
        return withContext(Dispatchers.IO) {

            val response: Response<List<EmpleadoModel>> =
                retrofit.create(EmpleadosApiClient::class.java).getEmpelados()
            response.body() ?: emptyList()
        }
    }

    suspend fun getEmpleadosPorId(id:String): EmpleadoModel {
        return withContext(Dispatchers.IO) {

            val response: Response<EmpleadoModel> =
                retrofit.create(EmpleadosApiClient::class.java).getEmpeladosPorId(id)
            response.body() ?: EmpleadoModel(0,"","",0.0,0.0,"","","")
        }
    }

    suspend fun postEmpleados(empleadoModelPost: EmpleadoModelPost): EmpleadoModel {
        return withContext(Dispatchers.IO) {

            val response: Response<EmpleadoModel> =
                retrofit.create(EmpleadosApiClient::class.java).postEmpleados(empleadoModelPost)
            response.body() ?: EmpleadoModel(0,"","",0.0,0.0,"","","")
        }
    }

    suspend fun editEmpleados(id: String,empleadoModelPost: EmpleadoModelPost): EmpleadoModel {
        return withContext(Dispatchers.IO) {

            val response: Response<EmpleadoModel> =
                retrofit.create(EmpleadosApiClient::class.java).editEmpleados(id,empleadoModelPost)
            response.body() ?: EmpleadoModel(0,"","",0.0,0.0,"","","")
        }
    }

    suspend fun deleteEmpleados(id:String): EmpleadoModel {
        return withContext(Dispatchers.IO) {

            val response: Response<EmpleadoModel> =
                retrofit.create(EmpleadosApiClient::class.java).deleteEmpleados(id)
            response.body() ?: EmpleadoModel(0,"","",0.0,0.0,"","","")
        }
    }

}