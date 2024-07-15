package com.genxsol.home.presentation

import com.genxsol.core.navigation.NavigationService
import com.genxsol.home.domain.model.HomeSections
import com.genxsol.home.domain.model.HomeSectionAdapterItem
import com.genxsol.home.domain.model.ProductItem
import com.genxsol.home.domain.usecase.GetInitialHomeUseCase
import com.genxsol.home.presentation.uievent.HomeUIEvent
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var viewModel: HomeViewModel
    private val getInitialHomeUseCase: GetInitialHomeUseCase = mockk()
    private val navigator: NavigationService = mockk(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(getInitialHomeUseCase, navigator)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when LoadInitialHome event is handled, state should be updated correctly`() = runBlocking {
        val homeSections = HomeSections(
            sections = listOf(
                HomeSectionAdapterItem.Banner(
                    bannerItem = listOf(),
                    id = 1
                ),
                HomeSectionAdapterItem.SlidableProducts(
                    productItem = listOf(),
                    sectionTitle = "Slidable",
                    id = 2
                ),
                HomeSectionAdapterItem.VerticalProducts(
                    productItem = listOf(),
                    sectionTitle = "Vertical",
                    id = 3
                )
            )
        )

        val flow = flow {
            emit(homeSections)
        }

        coEvery { getInitialHomeUseCase.getInitialHome() } returns flow

        // Simulate the event through a public method or state change
        viewModel.onEvent(HomeUIEvent.LoadInitialHome)

        coVerify { getInitialHomeUseCase.getInitialHome() }

        // Simulate flow emission
        flow.collect {
            assertEquals(homeSections, viewModel.uiState.value.homeData)
            assertEquals(false, viewModel.uiState.value.isLoading)
        }
    }

    @Test
    fun `when OnBannerClicked event is handled, navigateTo should be called with list`() = runBlocking {
        viewModel.onEvent(HomeUIEvent.OnBannerClicked)

        coVerify { navigator.navigateTo("list") }
    }

    @Test
    fun `when OnProductClicked event is handled, navigateTo should be called with list`() = runBlocking {
        viewModel.onEvent(HomeUIEvent.OnProductClicked)

        coVerify { navigator.navigateTo("list") }
    }

    @Test
    fun `when OnVerticalProductClicked event is handled, state should be updated correctly`() = runBlocking {
        val productItem = ProductItem(
            productId = "123",
            productImage = "image_url",
            text = "Product Text",
            subText = "Product SubText",
            review = "Review",
            questions = "Questions",
            rating = "4.5",
            share = "Share",
            piece = "1 piece",
            soldOutText = "Sold Out"
        )

        viewModel.onEvent(HomeUIEvent.OnVerticalProductClicked(productItem))

        assertEquals(productItem, viewModel.uiState.value.selectedProductItem)
    }

    @Test
    fun `when Dismiss event is handled, goBack should be called`() = runBlocking {
        viewModel.onEvent(HomeUIEvent.Dismiss)

        coVerify { navigator.goBack() }
    }
}
