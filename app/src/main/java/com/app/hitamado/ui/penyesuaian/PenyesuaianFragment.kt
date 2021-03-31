package com.app.hitamado.ui.penyesuaian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.hitamado.R
import com.kaopiz.kprogresshud.KProgressHUD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_penyesuaian.*
import java.lang.Exception

@AndroidEntryPoint
class PenyesuaianFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private lateinit var pb :KProgressHUD
    private val viewModel:PenyesuaianViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penyesuaian, container, false)
    }
    private var uidBarang:String? =null
    private var aksi:String?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var listBarangName = ArrayList<String>()
        var listUidBarang = ArrayList<String>()

        val listAksi=ArrayList<String>()

        viewModel.getDataEntity().observe(requireActivity(), Observer { result ->
            listBarangName.add("Pilih Barang")
            listUidBarang.add("0")
            if (result.barang != null) {
                for (data in result.barang) {
                    data?.namaBarang?.let { listBarangName.add(it) }
                    listUidBarang.add(data?.id.toString())
                }
            }
            var spinnerBarangAdapter =
                ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listBarangName
                )
            spinnerBarangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            try {
                spinner_delivery_barang.adapter = spinnerBarangAdapter
            }catch (e:Exception){}

        })
        listAksi.add("Pilih Aksi")
        listAksi.addAll(resources.getStringArray(R.array.aksi))
        var spinnerAksiAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listAksi
        )
        spinnerAksiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_aksi_penyesuaian.adapter=spinnerAksiAdapter

        spinner_aksi_penyesuaian.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                aksi=listAksi[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        spinner_delivery_barang.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
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
         pb= KProgressHUD.create(requireContext())
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(1)
            .setDimAmount(0.3f)
        btn_proses_penyesuaian_barang.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_proses_penyesuaian_barang->{

                pb.show()
                if (edt_jumlah_barang.text.isNullOrEmpty()){
                    pb.dismiss()
                    Toast.makeText(requireContext(),"Field Jumlah Barang Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show()
                }else if (edt_harga_barang.text.isNullOrEmpty()){
                    pb.dismiss()
                    Toast.makeText(requireContext(),"Field Harga Barang Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show()
                }else if (tv_description_penyesuaian.text.isNullOrEmpty()){
                    pb.dismiss()
                    Toast.makeText(requireContext(),"Field Keteranga Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show()
                }
                else if (uidBarang==null || uidBarang=="0"){
                    pb.dismiss()
                    Toast.makeText(requireContext(),"Pilih Barang Terlebih Dahulu!",Toast.LENGTH_SHORT).show()
                }else if (aksi==null || aksi=="Pilih Aksi"){
                    pb.dismiss()
                    Toast.makeText(requireContext(),"Pilih Aksi Terlebih Dahulu!",Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModel.postInsertPenyesuaian(uidBarang!!,aksi!!,edt_jumlah_barang.text.toString(),edt_harga_barang.text.toString(),tv_description_penyesuaian.text.toString()).observe(requireActivity(),
                        Observer {result->
                            if (result.status!=null || result.status!="failed"){
                                pb.dismiss()
                                Toast.makeText(requireContext(),"Berhasil Melakukan Penyesuaian",Toast.LENGTH_SHORT).show()
                                edt_jumlah_barang.text.clear()
                                edt_harga_barang.text.clear()
                                tv_description_penyesuaian.text.clear()
                            }
                        })
                }
            }
        }
    }

}