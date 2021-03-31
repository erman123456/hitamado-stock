package com.app.hitamado.model.packing

import com.app.hitamado.model.antarbarang.Barang
import com.google.gson.annotations.SerializedName

data class PackingBarangResponse(
    @SerializedName("barang")
    val barang: Barang? = null,

    @SerializedName("status")
    val status: String? = null
)