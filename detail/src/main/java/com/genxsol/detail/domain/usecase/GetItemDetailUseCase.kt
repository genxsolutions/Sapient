package com.genxsol.detail.domain.usecase

import com.genxsol.detail.domain.model.ItemDetail
import kotlinx.coroutines.flow.Flow

interface GetItemDetailUseCase {
    fun getDetail(): Flow<ItemDetail>
}