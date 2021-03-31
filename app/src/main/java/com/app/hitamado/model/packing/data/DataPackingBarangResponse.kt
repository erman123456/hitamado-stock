package com.app.hitamado.model.packing.data

import com.app.hitamado.model.antarbarang.data.BarangItem
import com.app.hitamado.model.antarbarang.data.CustomerItem
import com.google.gson.annotations.SerializedName

data class DataPackingBarangResponse(
    @field:SerializedName("barang")
    val barang: List<BarangItem?>? = null,
    @field:SerializedName("customer")
    val customer: List<CustomerItem?>? = null
)