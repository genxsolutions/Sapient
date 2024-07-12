package com.genxsol.detail.data.api.datasource

import com.genxsol.network.extensions.handleCall
import com.genxsol.detail.data.api.DetailApi
import com.genxsol.detail.data.api.model.ItemDetailResponse
import javax.inject.Inject

internal class DetailDataSourceImpl @Inject constructor(
    private val api: DetailApi
) : DetailDataSource {

    override suspend fun getDetail(): ItemDetailResponse {
        return handleCall {
            api.getDetail()
        }
    }
}