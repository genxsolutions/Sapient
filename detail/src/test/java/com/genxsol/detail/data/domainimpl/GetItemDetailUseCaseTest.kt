package com.genxsol.detail.data.domainimpl

import com.genxsol.detail.data.api.datasource.DetailDataSource
import com.genxsol.detail.data.api.model.ItemDetailResponse
import com.genxsol.detail.data.api.model.OtherProductResponse
import com.genxsol.detail.data.domain_impl.mapper.mapToItemDetail
import com.genxsol.detail.data.domain_impl.usecase.GetItemDetailUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetItemDetailUseCaseTest {

    @MockK
    private lateinit var dataSource: DetailDataSource

    private lateinit var getItemDetailUseCaseImpl: GetItemDetailUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = mockk()
        getItemDetailUseCaseImpl = GetItemDetailUseCaseImpl(dataSource, Dispatchers.Unconfined)
    }

    @Test
    fun `getDetail returns correct data`() = runTest {
        // Arrange
        val mockOtherProducts = listOf(
            OtherProductResponse(
                productImage = "image1.jpg",
                productName = "Product 1",
                subText = "Subtext 1"
            ),
            OtherProductResponse(
                productImage = "image2.jpg",
                productName = "Product 2",
                subText = "Subtext 2"
            )
        )
        val mockResponse = ItemDetailResponse(
            productId = "123",
            productImage = "image.jpg",
            productName = "Test Product",
            subText = "Test Subtext",
            share = "Share Text",
            productOptions = listOf("Option1", "Option2"),
            otherProducts = mockOtherProducts
        )

        coEvery { dataSource.getDetail() } returns mockResponse

        // Act
        val result = getItemDetailUseCaseImpl.getDetail().first()

        // Assert
        assertEquals(mockResponse.mapToItemDetail(), result)
        verify { runBlocking { dataSource.getDetail() } }
    }

}