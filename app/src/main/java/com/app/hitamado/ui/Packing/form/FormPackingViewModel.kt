package com.app.hitamado.ui.Packing.form

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.hitamado.data.source.SjtRepository
import com.app.hitamado.model.antarbarang.insert.ResponsePost
import com.app.hitamado.model.packing.data.DataPackingBarangResponse

class FormPackingViewModel@ViewModelInject constructor(private val sjtRepository: SjtRepository) :
    ViewModel() {
    fun getDataEntity():LiveData<DataPackingBarangResponse> = sjtRepository.getDataPacking()
    fun postInsertPacking(uid_customer: String, id_barang: String, jumlah_barang: String,keterangan: String):LiveData<ResponsePost> = sjtRepository.postInsertPacking(uid_customer, id_barang, jumlah_barang, keterangan)
}