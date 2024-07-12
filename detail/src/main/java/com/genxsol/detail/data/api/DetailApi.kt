package com.example.detail.data.api

import com.example.detail.data.api.model.ItemDetailResponse
import retrofit2.Response
import retrofit2.http.GET

interface DetailApi {
    @GET("/genxsol/api/main/detail-page")
    suspend fun getDetail(): Response<ItemDetailResponse>
}