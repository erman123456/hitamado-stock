package com.app.hitamado.ui.Delivery.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.app.hitamado.R
import com.kaopiz.kprogresshud.KProgressHUD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_form_delivery.*
import kotlinx.android.synthetic.main.activity_form_delivery.btn_back
import kotlinx.android.synthetic.main.activity_form_delivery.edt_jumlah_barang

@AndroidEntryPoint
class FormDeliveryActivity : AppCompatActivity() {

    private val viewModel:FormDeliveryViewModel by viewModels()
    private lateinit var pb :KProgressHUD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_delivery)
        var listCustomerName = ArrayList<String>()
        var listUidCustomer = ArrayList<String>()
        var uidCostomer:String? =null

        var listBarangName = ArrayList<String>()
        var listUidBarang = ArrayList<String>()
        var uidBarang:String? =null

        var listDriverName = ArrayList<String>()
        var listUidDriver = ArrayList<String>()
        var uidDriver:String? =null

        viewModel.getDataEntity().observe(this, Observer { result->
            listBarangName.add("Pilih Barang")
            listUidBarang.add("0")
            listCustomerName.add("Pilih Customer")
            listUidCustomer.add("0")
            listDriverName.add("Pilih Supir")
            listUidDriver.add("0")
            if (result.barang!=null){
                for(data in result.barang){
                    data?.namaBarang?.let { listBarangName.add(it) }
                    listUidBarang.add(data?.id.toString())
                }
            }
            if (result.customer!=null){
                for(data in result.customer){
                    data?.nama?.let { listCustomerName.add(it) }
                    listUidCustomer.add(data?.uid.toString())
                }
            }
            if (result.supir!=null){
                for(data in result.supir){
                    data?.nama?.let { listDriverName.add(it) }
                    listUidDriver.add(data?.uid.toString())
                }
            }
            var spinnerCustomerAdapter =
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listCustomerName)
            spinnerCustomerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_delivery_customer.adapter = spinnerCustomerAdapter

            var spinnerBarangAdapter =
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listBarangName)
            spinnerBarangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_delivery_barang.adapter = spinnerBarangAdapter

            var spinnerDriverAdapter =
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listDriverName)
            spinnerDriverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_delivery_driver.adapter = spinnerDriverAdapter
        })
        spinner_delivery_customer.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                uidCostomer=listUidCustomer[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner_delivery_barang.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                uidBarang=listUidBarang[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spinner_delivery_driver.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                uidDriver=listUidDriver[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        pb=KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(1)
            .setDimAmount(0.3f)

        btn_proses_antar_barang.setOnClickListener{
            pb.show()
            if (edt_jumlah_barang.text.isNullOrEmpty()){
                pb.dismiss()
                Toast.makeText(this,"Field Jumlah Barang Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show()
            }else if (uidBarang==null || uidBarang=="0"){
                pb.dismiss()
                Toast.makeText(this,"Field Barang Belum dipilih!",Toast.LENGTH_SHORT).show()
            }else if (uidCostomer==null || uidCostomer=="0"){
                pb.dismiss()
                Toast.makeText(this,"Field Customer Belum dipilih!",Toast.LENGTH_SHORT).show()
            }else if (uidDriver==null || uidCostomer=="0"){
                pb.dismiss()
                Toast.makeText(this,"Field Driver Belum dipilih!",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.postInsertBarang(uidCostomer!!,uidBarang!!,edt_jumlah_barang.text.toString(),uidDriver!!,tv_description.text.toString()).observe(this,
                    Observer { result->
                        Log.d("tesResult",result.status.toString())
                        if (result.status!="failed" && result.status!=null){
                            Log.d("coba", result.status.toString())
                            pb.dismiss()
                            Toast.makeText(this,"Data Disimpan..",Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this,"Gagal Terhubung ke server..", Toast.LENGTH_SHORT).show()
                        }
                    })
            }

        }
        btn_back.setOnClickListener{
            finish()
        }
    }

}