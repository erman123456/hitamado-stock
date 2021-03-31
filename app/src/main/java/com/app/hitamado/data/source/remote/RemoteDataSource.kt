package com.app.hitamado.data.source.remote

import android.util.Log
import com.app.hitamado.data.api.ApiClient
import com.app.hitamado.model.antarbarang.AntarBarangResponse
import com.app.hitamado.model.antarbarang.Barang
import com.app.hitamado.model.antarbarang.data.DataBarangResponse
import com.app.hitamado.model.antarbarang.insert.ResponsePost
import com.app.hitamado.model.login.ResponseAccount
import com.app.hitamado.model.packing.PackingBarangResponse
import com.app.hitamado.model.packing.data.DataPackingBarangResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun loadAntarBarang(page:Int=1,listAntarBarangCallback: ListAntarBarangCallback){

        val client =ApiClient.provideApiService()
        client.getAntarBarang(page = page ).enqueue(object : Callback<AntarBarangResponse>{
            override fun onFailure(call: Call<AntarBarangResponse>, t: Throwable) {
                Log.d("Gagal : ",t.message.toString())
                listAntarBarangCallback.onFailed(t)
            }

            override fun onResponse(
                call: Call<AntarBarangResponse>,
                response: Response<AntarBarangResponse>
            ) {
                if (response.isSuccessful){
                    if(response.body()?.barang?.data?.isEmpty()==true){
                        listAntarBarangCallback.onFailed(Throwable("Empty Response"))
                    }else{
                        listAntarBarangCallback.onSuccess(response.body()?.barang?:Barang())
                    }
                }else{
                    listAntarBarangCallback.onFailed(Throwable("Something error with response"))
                }
            }

        })

    }

    fun loadPackingBarang(page:Int=1,listPackingBarangCallback: ListPackingBarangCallback){
        val client =ApiClient.provideApiService()
        client.getPackingBarang(page = page).enqueue(object :Callback<PackingBarangResponse>{
            override fun onFailure(call: Call<PackingBarangResponse>, t: Throwable) {
                Log.d("Gagal : ",t.message.toString())
                listPackingBarangCallback.onFailed(t)
            }

            override fun onResponse(
                call: Call<PackingBarangResponse>,
                response: Response<PackingBarangResponse>
            ) {
                if (response.isSuccessful){
                    if(response.body()?.barang?.data?.isEmpty()==true){
                        listPackingBarangCallback.onFailed(Throwable("Empty Response"))
                    }else{
                        listPackingBarangCallback.onSuccess(response.body()?.barang?:Barang())
                    }
                }else{
                    listPackingBarangCallback.onFailed(Throwable("Something error with response"))
                }
            }

        })
    }

    fun loadDataBarang(getDataBarangCallback: GetDataBarangCallback){
        val client=ApiClient.provideApiService()
        client.getDataAntarBarang().enqueue(object :Callback<DataBarangResponse>{
            override fun onResponse(
                call: Call<DataBarangResponse>,
                response: Response<DataBarangResponse>
            ) {
                getDataBarangCallback.onGetResponseDataBarang(response.body())
            }

            override fun onFailure(call: Call<DataBarangResponse>, t: Throwable) {
                Log.d("errorLoadDataAntar","Gagal Terhubung Ke server")
            }

        })
    }
    fun loadDataPackingBarang(getPackingBarangCallback: GetPackingBarangCallback){
        val client=ApiClient.provideApiService()
        client.getDataPackingBarang().enqueue(object :Callback<DataPackingBarangResponse>{
            override fun onResponse(
                call: Call<DataPackingBarangResponse>,
                response: Response<DataPackingBarangResponse>
            ) {
                getPackingBarangCallback.onGetResponsePackingBarang(response.body())
            }

            override fun onFailure(call: Call<DataPackingBarangResponse>, t: Throwable) {
                Log.d("errorLoadPacking","Gagal Terhubung Ke server")
            }

        })
    }
    fun loadResponseLogin(email:String,password:String,callback:PostLoginCallback){
        val client=ApiClient.provideApiService()
        client.doLogin(email, password).enqueue(object :Callback<ResponseAccount>{
            override fun onResponse(
                call: Call<ResponseAccount>,
                response: Response<ResponseAccount>
            ) {
                callback.onGetResponseLogin(response.body())
            }

            override fun onFailure(call: Call<ResponseAccount>, t: Throwable) {
                Log.d("error","Gagal Terhubung Ke server")
            }

        })
    }
    fun loadResponseInsertBarang(uid_customer:String,id_barang:String,jumlah_barang:String,uid_supir:String,keterangan:String,callback:PostInsertCallback){
        val client=ApiClient.provideApiService()
        client.postInsertBarang(uid_customer,id_barang.toInt(),jumlah_barang.toInt(),uid_supir,keterangan).enqueue(object :Callback<ResponsePost>{
            override fun onResponse(call: Call<ResponsePost>, response: Response<ResponsePost>) {
                callback.onGetResponsePost(response.body())
            }

            override fun onFailure(call: Call<ResponsePost>, t: Throwable) {
                Log.d("errorInsertBarang","Gagal Terhubung Ke server : ${t.message.toString()}")
            }
        })
    }
    fun loadResponseInsertPacking(uid_customer:String,id_barang:String,jumlah_barang:String,keterangan:String,callback:PostInsertCallback){
        val client=ApiClient.provideApiService()
        client.postInsertpacking(uid_customer,id_barang.toInt(),jumlah_barang.toInt(),keterangan).enqueue(object :Callback<ResponsePost>{
            override fun onResponse(call: Call<ResponsePost>, response: Response<ResponsePost>) {
                callback.onGetResponsePost(response.body())
            }

            override fun onFailure(call: Call<ResponsePost>, t: Throwable) {
                Log.d("error","Gagal Terhubung Ke server")
            }
        })
    }

    fun loadResponseInsertPenyesuaian(id_barang: String,aksi:String,jumlah_barang: String,harga:String,keterangan: String,callback:PostInsertCallback){
        val client=ApiClient.provideApiService()
        client.postPenyesuaian(id_barang,aksi,jumlah_barang,harga,keterangan).enqueue(object :Callback<ResponsePost>{
            override fun onResponse(call: Call<ResponsePost>, response: Response<ResponsePost>) {
                callback.onGetResponsePost(response.body())
            }

            override fun onFailure(call: Call<ResponsePost>, t: Throwable) {
                Log.d("error penyesuaian",t.message.toString())
            }

        })
    }
    interface ListAntarBarangCallback{
        fun onSuccess(barang: Barang)
        fun onFailed(throwable: Throwable)
    }

    interface ListPackingBarangCallback{
        fun onSuccess(barang: Barang)
        fun onFailed(throwable: Throwable)
    }
    interface GetDataBarangCallback{
        fun onGetResponseDataBarang(data:DataBarangResponse?)
    }
    interface GetPackingBarangCallback{
        fun onGetResponsePackingBarang(data:DataPackingBarangResponse?)
    }

    interface PostInsertCallback{
        fun onGetResponsePost(response:ResponsePost?)
    }
    interface PostLoginCallback{
        fun onGetResponseLogin(response:ResponseAccount?)
    }
}