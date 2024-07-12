package com.genxsol.list.data.api.di

import com.genxsol.list.data.api.ListApi
import com.genxsol.list.data.api.datasource.ListDataSource
import com.genxsol.list.data.api.datasource.ListDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideListApi(retrofit: Retrofit): ListApi = retrofit.create(ListApi::class.java)

    @Singleton
    @Provides
    internal fun provideInitialResponseDataSource(apiService: ListApi): ListDataSource {
        return ListDataSourceImpl(apiService)
    }

}