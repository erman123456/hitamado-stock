package com.app.hitamado.model.antarbarang

import com.google.gson.annotations.SerializedName

data class Barang(

	@SerializedName("data")
	val data: List<DataItem> = listOf(),

	@SerializedName("current_page")
	val currentPage: Int = 1
)