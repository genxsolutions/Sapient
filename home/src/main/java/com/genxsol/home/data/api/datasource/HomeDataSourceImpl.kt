package com.genxsol.home.data.api.datasource

import com.genxsol.network.extensions.handleCall
import com.genxsol.home.data.api.HomeApi
import com.genxsol.home.data.api.model.HomeResponse
import javax.inject.Inject

internal class HomeDataSourceImpl @Inject constructor(
    private val api: HomeApi
) : HomeDataSource {
    override suspend fun getHome(): HomeResponse {
        return handleCall {
            api.getHome()
        }
    }

}