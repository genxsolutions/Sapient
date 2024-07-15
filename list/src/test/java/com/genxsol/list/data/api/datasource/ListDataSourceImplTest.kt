package com.genxsol.list.data.api.datasource

import com.genxsol.core.model.GenericException
import com.genxsol.list.data.api.ListApi
import com.genxsol.list.data.api.model.ListProducts
import com.genxsol.list.data.api.model.ListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class ListDataSourceImplTest {

    private val mockListApi: ListApi = mockk()
    private val listDataSource = ListDataSourceImpl(mockListApi)

    @Test
    fun `test getList returns success response`() {
        // Mock data
        val mockListProducts = listOf(
            ListProducts(
                productId = "prod1",
                productImage = "image_url",
                text = "Product Text",
                subText = "Product SubText",
                review = "Review",
                questions = "Questions",
                rating = "4.5"
            )
        )
        val mockResponse = ListResponse(
            listResponse = mockListProducts,
            productLimit = 10,
            totalCount = 100
        )

        // Mock API call
        coEvery { mockListApi.getList() } returns Response.success(mockResponse)

        // Perform the function call
        val result = runBlocking { listDataSource.getList() }

        // Assert the result
        assertEquals(mockResponse, result)
    }

    @Test(expected = GenericException::class)
    fun `test getList throws IOException`() {
        // Mock API call to throw an IOException
        coEvery { mockListApi.getList() } throws IOException("API call failed")

        // Perform the function call
        val result = runBlocking { listDataSource.getList() }
    }
}


