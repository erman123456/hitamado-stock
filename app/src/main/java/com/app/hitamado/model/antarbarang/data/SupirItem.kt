package com.app.hitamado.model.antarbarang.data

import com.google.gson.annotations.SerializedName

data class SupirItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null
)