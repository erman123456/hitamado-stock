package com.app.hitamado.data.pagesource.packing

import androidx.paging.DataSource
import com.app.hitamado.model.antarbarang.DataItem

class PackingBarangPageDataSourceFactory (val packingBarangPageDataSource: PackingBarangPageDataSource):DataSource.Factory<Int,DataItem>(){

    override fun create(): DataSource<Int, DataItem> {
        return packingBarangPageDataSource
    }

}