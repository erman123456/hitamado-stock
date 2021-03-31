package com.app.hitamado.data.pagesource.antarbarang

import androidx.paging.DataSource
import com.app.hitamado.model.antarbarang.DataItem

class AntarBarangPageDataSourceFactory (val antarBarangPageDataSource: AntarBarangPageDataSource):DataSource.Factory<Int,DataItem>(){
    override fun create(): DataSource<Int, DataItem> {
        return antarBarangPageDataSource
    }

}