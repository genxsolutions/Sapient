package com.genxsol.home.data.api.datasource

import com.genxsol.home.data.api.model.HomeResponse

interface HomeDataSource {
    suspend fun getHome(): HomeResponse
}