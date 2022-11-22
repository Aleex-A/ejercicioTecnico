package com.ejerciciotecnico.ui.view

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ejerciciotecnico.CustomAdapter
import com.ejerciciotecnico.data.model.EmpleadoModelPost
import com.ejerciciotecnico.databinding.ActivityMainBinding
import com.ejerciciotecnico.ui.viewmodel.EmpleadoViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_view_design.view.*
import java.time.LocalDateTime


class MainActivity : AppCompatActivity(), CustomAdapter.OnEmpleadoClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    private val empleadoViewModel: EmpleadoViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        create()
        binding.add.setOnClickListener {
            crear()
        }




        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


//        binding.boton.setOnClickListener {
//           val emp = EmpleadoModelPost("Pedro","123",0.0,0.0,"","1998-07-13")
//         postEmpleado(emp)
//
//            editEmpleado("5",emp)
//
////
//        }


//        empleadoViewModel.deleteEmpleado("7")


//        empleadoViewModel.empleadoModel.observe(this, Observer {
//            binding.texto.text = it[0].fullName
//        })
//
//
//
//
        empleadoViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
//

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        empleadoViewModel.empledoEdit.observe(this) {
            val empleadoData = EmpleadoModelPost(it[1],it[2],0.0,0.0,"",it[3])
            if (it[0].isEmpty()){
                newEmpleado(empleadoData)
            }else{
                empleadoEdit(it[0],empleadoData)
            }

        }
    }

    override fun onTrashClick(id: String, fullName:String) {
        Toast.makeText(this, "Empleado $fullName eliminado", Toast.LENGTH_SHORT).show()
        empleadoViewModel.deleteEmpleado(id)
        refresh()
    }


    override fun onEditClick(id:String) {
            try {
                val bottomSheetFragment = BottomSheetFragment()
                bottomSheetFragment.show(supportFragmentManager,"BottomShets")
                empleadoViewModel.getEmpleadoPorId(id)
                empleadoViewModel.empleadoModelPorIdReturn.observe(this, Observer {
                    empleadoViewModel.setEmpleadoinfo(it)
                })
            }catch (_:Exception){

            }
    }

    fun create(){
        empleadoViewModel.onCreate()
        val recyclerview = binding.recyclerview
        recyclerview.layoutManager = LinearLayoutManager(this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerview.setLayoutManager(linearLayoutManager)


        empleadoViewModel.empleadoModel.observe(this, Observer {
            val adapter = CustomAdapter(it,this)
            recyclerview.adapter = adapter

        })
    }


    fun crear(){
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager,"BottomShets")
    }


    @RequiresApi(Build.VERSION_CODES.O)
     private fun newEmpleado(newE: EmpleadoModelPost) {
         var lat: Double= 0.0
         var lng: Double= 0.0

         if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
         {
             ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION,
                 ACCESS_COARSE_LOCATION
             ),1)
         }else {


             try {
                 fusedLocationProviderClient.lastLocation
                     .addOnSuccessListener { location : Location? ->
                         // Obtiene la úlmtima localización conocida.
                         if (location != null) {

                             val utcDAte = LocalDateTime.now()
                             lat = location.latitude
                             lng = location.longitude
                             val emp = EmpleadoModelPost(newE.fullName,newE.avatar,lat,lng,utcDAte.toString(),newE.dateOfBirth)
                             empleadoViewModel.postEmpleado(emp)
                             Toast.makeText(this, "Empleado ${newE.fullName} agregado", Toast.LENGTH_SHORT).show()
                             refresh()
                         }
                     }
             } catch(ex: SecurityException) {
                 System.out.println("Security Exception, no location available" + ex.message)
             }
         }

     }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun empleadoEdit(id:String,empleadoData: EmpleadoModelPost) {
        var lat: Double= 0.0
        var lng: Double= 0.0

        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION
            ),1)
        }else {
            try {
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location : Location? ->
                        // Obtiene la úlmtima localización conocida.
                        if (location != null) {

                            val utcDAte = LocalDateTime.now()
                            lat = location.latitude
                            lng = location.longitude
                            val emp = EmpleadoModelPost(empleadoData.fullName,empleadoData.avatar,lat,lng,utcDAte.toString(),empleadoData.dateOfBirth)
                                empleadoViewModel.editEmpleado(id, emp)
                            Toast.makeText(this, "Empleado ${empleadoData.fullName} actualizado", Toast.LENGTH_SHORT).show()
                            refresh()
                        }
                    }
            } catch(ex: SecurityException) {
                System.out.println("Security Exception, no location available" + ex.message)
            }
        }

    }

    fun refresh(){
        create()
        finish()
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            if (grantResults.isNotEmpty() && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permiso ya otorgado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}