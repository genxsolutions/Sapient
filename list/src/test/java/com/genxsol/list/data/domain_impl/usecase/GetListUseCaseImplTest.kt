package com.genxsol.list.data.domain_impl.usecase

import com.genxsol.list.data.api.datasource.ListDataSource
import com.genxsol.list.data.api.model.ListProducts
import com.genxsol.list.data.api.model.ListResponse
import com.genxsol.list.data.domain_impl.mapper.mapToListData
import com.genxsol.list.domain.model.ListData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.EmptyCoroutineContext

@ExperimentalCoroutinesApi
class GetListUseCaseImplTest {

    private val mockDataSource: ListDataSource = mockk()
    private lateinit var getListUseCase: GetListUseCaseImpl

    @Before
    fun setUp() {
        getListUseCase = GetListUseCaseImpl(mockDataSource, EmptyCoroutineContext)
    }


    @Test
    fun `test getList returns ListData`() = runTest {
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

        // Mock behavior of ListDataSource
        coEvery { mockDataSource.getList() } returns mockResponse

        // Perform the function call
        val flowResult: Flow<ListData> = getListUseCase.getList()

        // Await for the flow to complete and get the result
        val result = flowResult.first()

        // Assert the result
        assertEquals(mockResponse.mapToListData(), result)
    }
}