package com.genxsol.list.data.domain_impl.di

import com.genxsol.core.utils.IODispatcher
import com.genxsol.list.data.api.datasource.ListDataSource
import com.genxsol.list.data.domain_impl.usecase.GetListUseCaseImpl
import com.genxsol.list.domain.usecase.GetListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideGetInitialHomeUseCase(
        dataSource: ListDataSource,
        @IODispatcher dispatcher: CoroutineContext
    ): GetListUseCase {
        return GetListUseCaseImpl(dataSource, dispatcher)
    }
}