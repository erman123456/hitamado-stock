package com.app.hitamado.data.api

import com.app.hitamado.model.antarbarang.AntarBarangResponse
import com.app.hitamado.model.antarbarang.data.DataBarangResponse
import com.app.hitamado.model.antarbarang.insert.ResponsePost
import com.app.hitamado.model.login.ResponseAccount
import com.app.hitamado.model.packing.PackingBarangResponse
import com.app.hitamado.model.packing.data.DataPackingBarangResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("all-antar-barang?")
    fun getAntarBarang(@Query("page")page:Int=1):Call<AntarBarangResponse>

    @GET("all-packing-barang?")
    fun getPackingBarang(@Query("page")page:Int=1):Call<PackingBarangResponse>

    @GET("antar-barang")
    fun getDataAntarBarang():Call<DataBarangResponse>

    @GET("packing-barang")
    fun getDataPackingBarang():Call<DataPackingBarangResponse>

    @FormUrlEncoded
    @POST("login")
    fun doLogin(
        @Field("email")email:String,
        @Field("password")password:String
    ):Call<ResponseAccount>

    @FormUrlEncoded
    @POST("insert-barang")
    fun postInsertBarang(
        @Field("uid_customer")uid_customer:String,
        @Field("id_barang")id_barang:Int,
        @Field("jumlah_barang")jumlah:Int,
        @Field("uid_supir")uid_driver:String,
        @Field("keterangan")keterangan:String
    ):Call<ResponsePost>

    @FormUrlEncoded
    @POST("insert-packing")
    fun postInsertpacking(
        @Field("uid_customer")uid_customer:String,
        @Field("id_barang")id_barang:Int,
        @Field("jumlah_barang")jumlah:Int,
        @Field("keterangan")keterangan:String
    ):Call<ResponsePost>

    @FormUrlEncoded
    @POST("insert-penyesuaian")
    fun postPenyesuaian(
        @Field("id_barang")idBarang:String,
        @Field("aksi")aksi:String,
        @Field("jumlah_barang")jumlahBarang:String,
        @Field("harga")harga:String,
        @Field("keterangan")keterangan:String
    ):Call<ResponsePost>
}