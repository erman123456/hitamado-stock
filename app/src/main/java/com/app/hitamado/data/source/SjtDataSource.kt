package com.app.hitamado.data.source

import androidx.lifecycle.LiveData
import com.app.hitamado.model.antarbarang.data.DataBarangResponse
import com.app.hitamado.model.antarbarang.insert.ResponsePost
import com.app.hitamado.model.login.ResponseAccount
import com.app.hitamado.model.packing.data.DataPackingBarangResponse

interface SjtDataSource {
    fun getResponseLogin(email:String,password:String):LiveData<ResponseAccount>
    fun getDataBarang():LiveData<DataBarangResponse>
    fun getDataPacking():LiveData<DataPackingBarangResponse>
    fun postInsertBarang(uid_customer:String,id_barang:String,jumlah_barang:String,uid_supir:String,keterangan:String):LiveData<ResponsePost>
    fun postInsertPacking(uid_customer:String,id_barang:String,jumlah_barang:String,keterangan:String):LiveData<ResponsePost>
    fun postInsertPenyesuaian(id_barang: String,aksi:String,jumlah_barang: String,harga:String,keterangan: String):LiveData<ResponsePost>
}