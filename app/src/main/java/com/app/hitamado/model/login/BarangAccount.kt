package com.app.hitamado.model.login

import com.google.gson.annotations.SerializedName

data class BarangAccount(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("no_wa")
	val noWa: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)