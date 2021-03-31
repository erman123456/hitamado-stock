package com.app.hitamado.model.login

import com.google.gson.annotations.SerializedName

data class ResponseAccount(

	@field:SerializedName("barang")
	val barangAccount: BarangAccount? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)