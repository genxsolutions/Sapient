package com.genxsol.list.data.api.datasource

import com.genxsol.list.data.api.model.ListResponse
interface ListDataSource {
    suspend fun getList(): ListResponse
}