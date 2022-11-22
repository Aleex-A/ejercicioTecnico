package com.ejerciciotecnico.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ejerciciotecnico.R
import com.ejerciciotecnico.data.model.EmpleadoModel
import com.ejerciciotecnico.data.model.EmpleadoModelPost
import com.ejerciciotecnico.ui.viewmodel.EmpleadoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_fragment.*


class BottomSheetFragment: BottomSheetDialogFragment(){
    var day: Int = 0
    var month: Int = 0
    var year: Int = 0



    private val empleadoViewModel: EmpleadoViewModel by activityViewModels()
    private val empleadoModel = MutableLiveData<EmpleadoModel>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idlbl.text =""

        empleadoViewModel.dob.observe(this, Observer {

            DOBview.setText(it)

        })

        empleadoViewModel.editEmpleadoInfo.observe(this, Observer {
            idlbl.text = it.id
            name.setText(it.fullName)
            DOBview.setText(it.dateOfBirth)
            empleadoModel.postValue(it)
        })


        DOBview.setOnClickListener { showDatePckerDialog() }

        guardar.setOnClickListener {
            if(name.text.trim().isEmpty()){
                Toast.makeText(context,"Es necesario Registrar un Nombre",Toast.LENGTH_SHORT).show()
            } else if (DOBview.text.toString() == "----/--/--"){
                Toast.makeText(context,"Es necesario Registrar una Fecha",Toast.LENGTH_SHORT).show()

            }else {
                empleadoViewModel.setEmpleadoEdit(
                    idlbl.text.toString(),
                    name.text.toString(),
                    "@drawable/cat_icon",
                    DOBview.text.toString()
                )
                dismiss()
                activity?.intent
            }
        }


    }

    private fun showDatePckerDialog(){


        val datePicker = DatePckerFragment{day,month,year-> onDateSelected(day, month, year) }
        datePicker.show(childFragmentManager,"datePicker")

    }

    @SuppressLint("SetTextI18n")
    fun onDateSelected(day:Int, month:Int, year:Int){

        empleadoViewModel.setDob(year.toString(),month.toString(),day.toString())
        DOBview.setText("$year/$month/$day")
    }

}