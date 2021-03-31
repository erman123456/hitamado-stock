package com.app.hitamado.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.hitamado.data.source.remote.RemoteDataSource
import com.app.hitamado.model.antarbarang.data.DataBarangResponse
import com.app.hitamado.model.antarbarang.insert.ResponsePost
import com.app.hitamado.model.login.ResponseAccount
import com.app.hitamado.model.packing.data.DataPackingBarangResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SjtRepository @Inject constructor(private val remoteDataSource: RemoteDataSource):SjtDataSource {
    override fun getResponseLogin(email: String, password: String): LiveData<ResponseAccount> {
        val result=MutableLiveData<ResponseAccount>()
        remoteDataSource.loadResponseLogin(email,password,object :RemoteDataSource.PostLoginCallback{
            override fun onGetResponseLogin(response: ResponseAccount?) {
                val data=ResponseAccount(
                    response?.barangAccount,
                    response?.message,
                    response?.status
                )
                result.postValue(data)
            }
        })
        return result
    }

    override fun getDataBarang(): LiveData<DataBarangResponse> {
        val dataBarang=MutableLiveData<DataBarangResponse>()
        remoteDataSource.loadDataBarang(object :RemoteDataSource.GetDataBarangCallback{
            override fun onGetResponseDataBarang(data: DataBarangResponse?) {
                val entity=DataBarangResponse(
                    data?.barang,
                    data?.supir,
                    data?.customer
                )
                dataBarang.postValue(entity)
            }
        })
        return dataBarang
    }

    override fun getDataPacking(): LiveData<DataPackingBarangResponse> {
        val dataPacking=MutableLiveData<DataPackingBarangResponse>()
        remoteDataSource.loadDataPackingBarang(object :RemoteDataSource.GetPackingBarangCallback{
            override fun onGetResponsePackingBarang(data: DataPackingBarangResponse?) {
                val entity=DataPackingBarangResponse(
                    data?.barang,
                    data?.customer
                )
                dataPacking.postValue(entity)
            }
        })
        return dataPacking
    }

    override fun postInsertBarang(
        uid_customer: String,
        id_barang: String,
        jumlah_barang: String,
        uid_supir: String,
        keterangan: String
    ): LiveData<ResponsePost> {
        val result=MutableLiveData<ResponsePost>()
        remoteDataSource.loadResponseInsertBarang(uid_customer,id_barang,jumlah_barang,uid_supir,keterangan,object :RemoteDataSource.PostInsertCallback{
            override fun onGetResponsePost(response: ResponsePost?) {
                val data=ResponsePost(
                    response?.status
                )
                result.postValue(data)
            }
        })
        return result
    }

    override fun postInsertPacking(
        uid_customer: String,
        id_barang: String,
        jumlah_barang: String,
        keterangan: String
    ): LiveData<ResponsePost> {
        val result=MutableLiveData<ResponsePost>()
        remoteDataSource.loadResponseInsertPacking(uid_customer,id_barang,jumlah_barang,keterangan,object :RemoteDataSource.PostInsertCallback{
            override fun onGetResponsePost(response: ResponsePost?) {
                val data=ResponsePost(
                    response?.status
                )
                result.postValue(data)
            }
        })
        return result
    }

    override fun postInsertPenyesuaian(
        id_barang: String,
        aksi: String,
        jumlah_barang: String,
        harga: String,
        keterangan: String
    ): LiveData<ResponsePost> {
        val result=MutableLiveData<ResponsePost>()
        remoteDataSource.loadResponseInsertPenyesuaian(id_barang,aksi,jumlah_barang,harga,keterangan,object :RemoteDataSource.PostInsertCallback{
            override fun onGetResponsePost(response: ResponsePost?) {
                val data=ResponsePost(
                    response?.status
                )
                result.postValue(data)
            }
        })
        return result
    }
}