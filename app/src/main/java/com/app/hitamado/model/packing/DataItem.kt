package com.app.hitamado.model.packing

import com.google.gson.annotations.SerializedName

data class DataItem(

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("nama_pemesan_barang")
	val namaPemesan:String? = null,

	@SerializedName("nama_barang")
	val namaBarang:String? = null,

	@SerializedName("kode_barang")
	val kodeBarang:String? = null,

	@SerializedName("tanggal")
	val tanggal:String? = null,

	@SerializedName("masuk")
	val masuk:String? = null,

	@SerializedName("keluar")
	val keluar:String? = null,

	@SerializedName("sisa")
	val sisa:String? = null

)