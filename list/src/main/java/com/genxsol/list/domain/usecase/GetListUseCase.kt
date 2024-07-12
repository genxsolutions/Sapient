package com.genxsol.list.domain.usecase

import com.genxsol.list.domain.model.ListData
import kotlinx.coroutines.flow.Flow

interface GetListUseCase {
    fun getList(): Flow<ListData>
}