package com.genxsol.detail.data.api

import com.genxsol.detail.data.api.model.ItemDetailResponse
import retrofit2.Response
import retrofit2.http.GET

interface DetailApi {
    @GET("/genxsolutions/api-json/main/detail-page")
    suspend fun getDetail(): Response<ItemDetailResponse>
}