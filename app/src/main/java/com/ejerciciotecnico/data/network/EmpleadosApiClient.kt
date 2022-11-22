package com.ejerciciotecnico.data.network

import com.ejerciciotecnico.data.model.EmpleadoModel
import com.ejerciciotecnico.data.model.EmpleadoModelPost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EmpleadosApiClient {
    @GET(" ")
    suspend fun getEmpelados():Response<List<EmpleadoModel>>

    @GET("{id}")
    suspend fun getEmpeladosPorId(@Path("id") id: String):Response<EmpleadoModel>

    @POST(" ")
    suspend fun postEmpleados( @Body empleadoModelPost: EmpleadoModelPost):Response<EmpleadoModel>

    @PUT("{id}")
    suspend fun editEmpleados(@Path("id") id: String ,@Body empleadoModelPost: EmpleadoModelPost):Response<EmpleadoModel>

    @DELETE("{id}")
    suspend fun deleteEmpleados(@Path("id") id: String):Response<EmpleadoModel>
}