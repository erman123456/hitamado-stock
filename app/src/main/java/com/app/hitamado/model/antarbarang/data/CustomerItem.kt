package com.app.hitamado.model.antarbarang.data

import com.google.gson.annotations.SerializedName

data class CustomerItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("uid_pegawai")
	val uidPegawai: String? = null
)