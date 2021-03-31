package com.app.hitamado.model.antarbarang.data

import com.google.gson.annotations.SerializedName

data class BarangItem(

	@field:SerializedName("nama_barang")
	val namaBarang: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)