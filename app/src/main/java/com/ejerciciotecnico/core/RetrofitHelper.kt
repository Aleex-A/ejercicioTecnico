package com.ejerciciotecnico.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://636a9855c07d8f936da2ad92.mockapi.io/api/v1/employees/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}