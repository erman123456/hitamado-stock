package com.app.hitamado.model.antarbarang

import com.google.gson.annotations.SerializedName

data class AntarBarangResponse(

	@SerializedName("barang")
	val barang: Barang? = null,

	@SerializedName("status")
	val status: String? = null
)