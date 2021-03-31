package com.app.hitamado.di

import com.app.hitamado.data.source.SjtRepository
import com.app.hitamado.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource():RemoteDataSource= RemoteDataSource()

    @Singleton
    @Provides
    fun provideSjtRepository(remoteDataSource: RemoteDataSource): SjtRepository =
        SjtRepository(remoteDataSource)

}