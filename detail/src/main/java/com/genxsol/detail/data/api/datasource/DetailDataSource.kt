package com.genxsol.detail.data.api.datasource

import com.genxsol.detail.data.api.model.ItemDetailResponse

interface DetailDataSource {
    suspend fun getDetail(): ItemDetailResponse
}


