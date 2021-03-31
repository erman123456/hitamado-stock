package com.app.hitamado.ui.Delivery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.hitamado.data.pagesource.antarbarang.AntarBarangPageDataSource
import com.app.hitamado.data.pagesource.antarbarang.AntarBarangPageDataSourceFactory
import com.app.hitamado.data.source.remote.RemoteDataSource
import com.app.hitamado.model.antarbarang.DataItem
import java.util.concurrent.Executors

class DeliveryViewModel @ViewModelInject constructor(private val remoteDataSource: RemoteDataSource):ViewModel() {


    private var barang:LiveData<PagedList<DataItem>>?= null
    private var loadingState:LiveData<Boolean>? = null
    private var loadMoreLoadingState:LiveData<Boolean>? = null
    private lateinit var factory: AntarBarangPageDataSourceFactory
    init {

    }
    private fun createConfig(): PagedList.Config {
        val configBuilder = PagedList.Config.Builder()
        return configBuilder
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPrefetchDistance(4)
            .setPageSize(4)
            .build()
    }

    fun init(){
        val antarBarangPageDataSource = AntarBarangPageDataSource(remoteDataSource)
        factory = AntarBarangPageDataSourceFactory(antarBarangPageDataSource)
        val dataBuilder = LivePagedListBuilder<Int,DataItem>(factory,createConfig())
        barang = dataBuilder.setFetchExecutor(Executors.newFixedThreadPool(5)).build()
        loadingState =antarBarangPageDataSource.getLoadingState()
        loadMoreLoadingState = antarBarangPageDataSource.getLoadMoreLoadingState()
    }
    fun getAntarBarang() = barang

    fun getLoadingState() = loadingState

    fun getMoreLoadingState() = loadMoreLoadingState

    fun refresh()=factory.antarBarangPageDataSource.invalidate()
}