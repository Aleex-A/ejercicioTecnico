package com.ejerciciotecnico.ui.viewmodel

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ejerciciotecnico.data.model.EmpleadoModel
import com.ejerciciotecnico.data.model.EmpleadoModelPost
import com.ejerciciotecnico.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Month

class EmpleadoViewModel  :ViewModel() {

    val empleadoModel = MutableLiveData<List<EmpleadoModel>>()
    val isLoading = MutableLiveData<Boolean>()

    val empleadoModelPorIdReturn = MutableLiveData<EmpleadoModel>()
    val empleadoEditReturn = MutableLiveData<EmpleadoModel>()
    val empleadoDeleteReturn = MutableLiveData<EmpleadoModel>()
    val empleadoModelPostReturn = MutableLiveData<EmpleadoModel>()

    val editEmpleadoInfo = MutableLiveData<EmpleadoModel>()

    val dob = MutableLiveData<String>()
    val empledoEdit = MutableLiveData<List<String>>()

    var getEmpleadosCase = GetEmpleadoCase()



    fun clear(){
        val listEmpleadoEmpty: List<EmpleadoModel> = emptyList()
        val empleadoEmpty: EmpleadoModel = EmpleadoModel(0,"","",0.0,0.0,"","","",)
        empleadoModel.postValue(listEmpleadoEmpty)
        empleadoEditReturn.postValue(empleadoEmpty)
        empleadoDeleteReturn.postValue(empleadoEmpty)
        empleadoModelPostReturn.postValue(empleadoEmpty)
        empleadoModelPorIdReturn.postValue(empleadoEmpty)
    }

    fun setEmpleadoinfo(empleadoModel: EmpleadoModel){
        editEmpleadoInfo.postValue(empleadoModel)
    }
    fun setDob(year:String,month:String,day:String){
        val date = "$year-$month-$day"
        dob.postValue(date)
    }
    fun setEmpleadoEdit(id:String,nombre:String,avatar:String,date:String){
        val empleadoEditList= listOf(id,nombre,avatar,date)
        empledoEdit.postValue(empleadoEditList)
    }

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val  result = getEmpleadosCase()

            if (!result.isNullOrEmpty()){
                empleadoModel.postValue(result!!)
                isLoading.postValue(false)

            }
        }

    }

    fun getEmpleadoPorId(id:String){

        val getEmpleadoPorIdCase = GetEmpleadoPorIdCase(id)
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getEmpleadoPorIdCase()

            if (result?.id != "") {
                empleadoModelPorIdReturn.postValue(result!!)
                isLoading.postValue(false)
            }
        }
    }

    fun postEmpleado(empleadoModelPost: EmpleadoModelPost){

        val postEmpleado = PostEmpleadoCase(empleadoModelPost)

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = postEmpleado()

            if (result?.id != "") {
                empleadoModelPostReturn.postValue(result!!)
                isLoading.postValue(false)
            }
        }
    }
    fun editEmpleado(id:String,empleadoModelPost: EmpleadoModelPost){

        val editEmpleado = EditEmpleadoCase(id,empleadoModelPost)

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = editEmpleado()

            if (result?.id != "") {
                empleadoEditReturn.postValue(result!!)
                isLoading.postValue(false)
            }
        }
    }

    fun deleteEmpleado(id:String){

        val deleteEmpleadoCase = DeleteEmpleadoCase(id)
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = deleteEmpleadoCase()

            if (result?.id != "") {
                empleadoDeleteReturn.postValue(result!!)
                isLoading.postValue(false)
            }
        }
    }


}