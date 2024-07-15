package com.genxsol.list.presentation

import com.genxsol.core.navigation.NavigationService
import com.genxsol.list.domain.model.ListData
import com.genxsol.list.domain.usecase.GetListUseCase
import com.genxsol.list.presentation.event.ListUIEvent
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var viewModel: ListViewModel
    private var getListUseCase: GetListUseCase = mockk()
    private var navigator: NavigationService = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = ListViewModel(getListUseCase, navigator)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getList success`() = runBlocking {
        // Mock data
        val mockListData = ListData(
            productList = emptyList(),
            productLimit = 10,
            totalCount = 100
        )

        // Mock behavior of GetListUseCase
        coEvery { getListUseCase.getList() } returns flowOf(mockListData)

        // Trigger the event
        viewModel.onEvent(ListUIEvent.GetList)

        assertEquals(viewModel.uiState.value.isLoading, false) // Assert loading state after data fetch
        assertEquals(viewModel.uiState.value.listData, mockListData) // Assert list data
    }

    @Test
    fun `test handleEvent dismiss`() {
        // Trigger the event
        viewModel.onEvent(ListUIEvent.Dismiss)

        // Verify that navigator.goBack() was called
        coVerify(exactly = 1) { navigator.goBack() }
    }
}
