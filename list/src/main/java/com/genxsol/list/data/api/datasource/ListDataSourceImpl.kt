package com.genxsol.list.data.api.datasource

import com.genxsol.list.data.api.model.ListResponse
import javax.inject.Inject
import com.genxsol.list.data.api.ListApi
import com.genxsol.network.extensions.handleCall

internal class ListDataSourceImpl @Inject constructor(
    private val api: ListApi
) : ListDataSource {
    override suspend fun getList(): ListResponse {
        return handleCall {
            api.getList()
        }
    }
}