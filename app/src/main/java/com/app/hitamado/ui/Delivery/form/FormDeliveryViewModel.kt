package com.app.hitamado.ui.Delivery.form

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.hitamado.data.source.SjtRepository
import com.app.hitamado.model.antarbarang.data.DataBarangResponse
import com.app.hitamado.model.antarbarang.insert.ResponsePost

class FormDeliveryViewModel @ViewModelInject constructor(private val sjtRepository: SjtRepository) :
    ViewModel() {
    fun getDataEntity():LiveData<DataBarangResponse> = sjtRepository.getDataBarang()
    fun postInsertBarang(uid_customer: String, id_barang: String, jumlah_barang: String, uid_supir: String, keterangan: String):LiveData<ResponsePost> = sjtRepository.postInsertBarang(uid_customer, id_barang, jumlah_barang, uid_supir, keterangan)
}