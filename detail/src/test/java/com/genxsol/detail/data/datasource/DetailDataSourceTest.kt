package com.genxsol.detail.data.datasource

import com.genxsol.core.model.GenericException
import com.genxsol.detail.data.api.DetailApi
import com.genxsol.detail.data.api.datasource.DetailDataSourceImpl
import com.genxsol.detail.data.api.model.ItemDetailResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class DetailDataSourceTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mockApi: DetailApi

    private lateinit var dataSource: DetailDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = DetailDataSourceImpl(mockApi)
    }

    @Test
    fun `getDetail returns valid response when api call is successful`() = runBlocking {
        // Given
        val expectedResponse = ItemDetailResponse(
            productId = "123",
            productImage = "image_url",
            productName = "Test Product",
            productOptions = listOf("Option 1", "Option 2"),
            share = "share_text",
            subText = "sub_text"
        )
        coEvery { mockApi.getDetail() } returns Response.success(expectedResponse)

        // Act
        val actualResponse = dataSource.getDetail()

        // Assert
        assertEquals(expectedResponse, actualResponse)
        verify { runBlocking { mockApi.getDetail() } }
    }

    @Test(expected = GenericException::class)
    fun `getDetail throws GenericException when api call is unsuccessful`(): Unit = runBlocking {
        // Arrange
        val errorResponse = Response.error<ItemDetailResponse>(404, ResponseBody.create("application/json".toMediaTypeOrNull(), ""))
        coEvery { mockApi.getDetail() } returns errorResponse

        // Act & Assert
        dataSource.getDetail()
    }
}