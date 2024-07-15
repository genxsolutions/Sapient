package com.genxsol.home.data.domainimpl.usecase

import com.genxsol.home.data.api.datasource.HomeDataSource
import com.genxsol.home.data.api.model.HomeResponse
import com.genxsol.home.data.api.model.HomeSection
import com.genxsol.home.data.api.model.Section
import com.genxsol.home.domain.model.HomeSectionAdapterItem
import com.genxsol.home.domain.model.HomeSections
import com.genxsol.home.domain.model.ProductItem
import com.genxsol.home.domain.usecase.GetInitialHomeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.EmptyCoroutineContext

class GetInitialHomeUseCaseImplTest {

    private lateinit var dataSource: HomeDataSource
    private lateinit var getInitialHomeUseCase: GetInitialHomeUseCase

    @Before
    fun setUp() {
        dataSource = mockk()
        getInitialHomeUseCase = GetInitialHomeUseCaseImpl(dataSource, EmptyCoroutineContext)
    }

    @Test
    fun `test getInitialHome emits expected HomeSections`() = runTest {
        // Mock data
        val homeSection = HomeSection(
            productId = "prod1",
            productImage = "image_url",
            text = "Product Text",
            subText = "Product SubText",
            review = "Review",
            questions = "Questions",
            rating = "4.5",
            share = "Share",
            piece = "1 piece",
            soldOutText = "Sold Out",
            image = "image_url"
        )
        val section = Section(
            type = 2,
            id = 102,
            sectionTitle = "Slidable Products",
            sectionData = listOf(homeSection)
        )
        val homeResponse = HomeResponse(sections = listOf(section))

        // Mock DataSource response
        coEvery { dataSource.getHome() } returns
            homeResponse

        // Expected result
        val expectedHomeSections = HomeSections(
            sections = listOf(
                HomeSectionAdapterItem.SlidableProducts(
                    viewType = HomeSectionAdapterItem.VIEW_TYPE_SLIDABLE_PRODUCTS,
                    productItem = listOf(
                        ProductItem(
                            productId = "prod1",
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
                    ),
                    sectionTitle = "Slidable Products",
                    id = 102
                )
            )
        )

        // Call the use case
        val result = getInitialHomeUseCase.getInitialHome().first()

        // Assert
        assertEquals(expectedHomeSections, result)
    }
}