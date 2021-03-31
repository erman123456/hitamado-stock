package com.app.hitamado.ui.penyesuaian

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.hitamado.data.source.SjtRepository
import com.app.hitamado.model.antarbarang.data.DataBarangResponse
import com.app.hitamado.model.antarbarang.insert.ResponsePost

class PenyesuaianViewModel@ViewModelInject constructor(private val sjtRepository: SjtRepository) :
    ViewModel() {
    fun getDataEntity(): LiveData<DataBarangResponse> = sjtRepository.getDataBarang()
    fun postInsertPenyesuaian(id_barang: String,aksi:String,jumlah_barang: String,harga:String,keterangan: String):LiveData<ResponsePost> = sjtRepository.postInsertPenyesuaian(id_barang, aksi, jumlah_barang, harga, keterangan)
}