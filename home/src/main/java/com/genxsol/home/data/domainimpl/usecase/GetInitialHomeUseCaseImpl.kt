package com.genxsol.home.data.domainimpl.usecase

import com.genxsol.core.utils.IODispatcher
import com.genxsol.home.data.api.datasource.HomeDataSource
import com.genxsol.home.data.domainimpl.mapper.mapToHomeSections
import com.genxsol.home.domain.model.HomeSections
import com.genxsol.home.domain.usecase.GetInitialHomeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class GetInitialHomeUseCaseImpl @Inject constructor(
    private val dataSource: HomeDataSource,
    @IODispatcher private val dispatcher: CoroutineContext
) : GetInitialHomeUseCase {
    override fun getInitialHome(): Flow<HomeSections> =
        flow {
            val initialData = dataSource.getHome().mapToHomeSections()
            emit(initialData)
        }.flowOn(dispatcher)
}