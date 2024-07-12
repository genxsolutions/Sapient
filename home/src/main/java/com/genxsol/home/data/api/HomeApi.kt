package com.genxsol.home.data.api

import com.genxsol.home.data.api.model.HomeResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("/genxsolutions/api-json/main/home")
    suspend fun getHome(): Response<HomeResponse>
}