package com.app.hitamado.data.pagesource.packing

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.app.hitamado.data.source.remote.RemoteDataSource
import com.app.hitamado.model.antarbarang.Barang
import com.app.hitamado.model.antarbarang.DataItem
import javax.inject.Inject

class PackingBarangPageDataSource @Inject constructor(private val remoteDataSource: RemoteDataSource):PageKeyedDataSource<Int,DataItem>(){

    private val loadingState = MutableLiveData<Boolean>()
    private val loadMoreLoadingState = MutableLiveData<Boolean>()

    fun getLoadingState() =loadingState
    fun getLoadMoreLoadingState() = loadMoreLoadingState
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataItem>
    ) {
        loadingState.postValue(true)

        remoteDataSource.loadPackingBarang(listPackingBarangCallback = object : RemoteDataSource.ListPackingBarangCallback{
            override fun onSuccess(barang: Barang) {
                callback.onResult(barang.data, null,barang.currentPage+1)
                loadingState.postValue(false)
            }

            override fun onFailed(throwable: Throwable) {
                callback.onResult(mutableListOf(),null,-1)
                loadingState.postValue(false)
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataItem>) {
        if (params.key==-1) return

        loadMoreLoadingState.postValue(true)

        remoteDataSource.loadPackingBarang(page = params.key,listPackingBarangCallback = object :RemoteDataSource.ListPackingBarangCallback{
            override fun onSuccess(barang: Barang) {
                callback.onResult(barang.data,barang.currentPage+1)
                loadMoreLoadingState.postValue(false)
            }

            override fun onFailed(throwable: Throwable) {
                callback.onResult(mutableListOf(),params.key+1)
                loadMoreLoadingState.postValue(false)
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataItem>) {
    }


}