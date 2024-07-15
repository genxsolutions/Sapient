package com.genxsol.list.data.domain_impl.mapper
import com.genxsol.list.data.api.model.ListProducts
import com.genxsol.list.data.api.model.ListResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class ListMapperTest {

    @Test
    fun `test mapToListData`() {
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

        // Perform the mapping
        val result = mockResponse.mapToListData()

        // Assertions
        assertEquals(mockListProducts.size, result.productList?.size)
        assertEquals(mockResponse.productLimit, result.productLimit)
        assertEquals(mockResponse.totalCount, result.totalCount)

        // Additional assertions for product details
        val firstProduct = result.productList?.first()
        assertEquals(mockListProducts.first().productId, firstProduct?.productId)
        assertEquals(mockListProducts.first().productImage, firstProduct?.productImage)
        assertEquals(mockListProducts.first().text, firstProduct?.text)
        assertEquals(mockListProducts.first().subText, firstProduct?.subText)
        assertEquals(mockListProducts.first().review, firstProduct?.review)
        assertEquals(mockListProducts.first().questions, firstProduct?.questions)
        assertEquals(mockListProducts.first().rating, firstProduct?.rating)
    }
}
