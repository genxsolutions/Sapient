package com.genxsol.detail.presentation

import com.genxsol.core.navigation.NavigationService
import com.genxsol.detail.domain.model.ItemDetail
import com.genxsol.detail.domain.model.OtherProducts
import com.genxsol.detail.domain.usecase.GetItemDetailUseCase
import com.genxsol.detail.presentation.state.DetailUIState
import com.genxsol.detail.presentation.uievent.DetailUIEvent
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    private var getItemDetail: GetItemDetailUseCase = mockk()

    @MockK
    private var navigator: NavigationService = mockk()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)  // Set the main dispatcher to the test dispatcher
        viewModel = DetailViewModel(getItemDetail, navigator)
    }

    @Test
    fun `loadItemDetail updates uiState correctly`() = runTest {
        val itemDetail = ItemDetail(
            productImage = "image_url",
            productName = "Test Product",
            productId = "123",
            subText = "sub_text",
            review = null,
            questions = null,
            share = "share_text",
            otherProducts = listOf(
                OtherProducts(
                    productImage = "other_product_image_url",
                    productName = "Other Product Name",
                    subText = "Other Product Sub Text"
                )
            ),
            productOptions = listOf("Option 1", "Option 2")
        )

        coEvery { getItemDetail.getDetail() } returns flowOf(itemDetail)

        val stateList = mutableListOf<DetailUIState>()
        val job = launch {
            viewModel.uiState.toList(stateList)
        }

        viewModel.onEvent(DetailUIEvent.LoadItemDetail)

        advanceUntilIdle()

        // Assert that uiState was updated correctly
        assertTrue("Expected state not found in stateList", stateList.any {
            it.itemData == itemDetail && !it.isLoading
        })

        job.cancel()
    }

    @Test
    fun `loadItemDetail updates uiState on error`() = runTest {
        val exception = RuntimeException("Test Exception")

        coEvery { getItemDetail.getDetail() } returns flow { throw exception }

        viewModel.onEvent(DetailUIEvent.LoadItemDetail)

        val currentState = viewModel.uiState.value
        assertTrue(currentState.error === exception)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()  // Reset the main dispatcher to the original one
        testDispatcher.cleanupTestCoroutines()
    }
}