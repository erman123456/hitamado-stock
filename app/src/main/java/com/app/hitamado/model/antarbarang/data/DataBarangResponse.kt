package com.app.hitamado.model.antarbarang.data

import com.google.gson.annotations.SerializedName

data class DataBarangResponse(

	@field:SerializedName("barang")
	val barang: List<BarangItem?>? = null,

	@field:SerializedName("supir")
	val supir: List<SupirItem?>? = null,

	@field:SerializedName("customer")
	val customer: List<CustomerItem?>? = null
)