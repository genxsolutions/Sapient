package com.genxsol.list.data.api

import com.genxsol.list.data.api.model.ListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListApi {

    @GET("/genxsolutions/api-json/main/list-page-paging-first")
    suspend fun getList() : Response<ListResponse>

}