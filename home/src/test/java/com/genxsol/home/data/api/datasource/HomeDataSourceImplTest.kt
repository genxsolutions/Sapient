package com.genxsol.home.data.api.datasource

import com.genxsol.core.model.GenericException
import com.genxsol.home.data.api.HomeApi
import com.genxsol.home.data.api.model.HomeResponse
import com.genxsol.home.data.api.model.HomeSection
import com.genxsol.home.data.api.model.Section
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class HomeDataSourceImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mockApi: HomeApi

    private lateinit var dataSource: HomeDataSourceImpl
    private lateinit var mockHomeResponse: HomeResponse

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = HomeDataSourceImpl(mockApi)

        // Mock response data
        val mockHomeSection = HomeSection(
            icon = "icon_url",
            image = "image_url",
            navigationData = "ND_49581L",
            productId = "PI_845481EI",
            productImage = "product_image_url",
            questions = "Some questions",
            rating = "4.5",
            review = "Some review",
            share = "Share this product",
            subText = "Some subtext",
            text = "Some text",
            piece = "Piece information",
            soldOutText = "Sold out"
        )

        val mockSection = Section(
            sectionData = listOf(mockHomeSection),
            sectionTitle = "Section Title",
            type = 1,
            id = 123
        )

        mockHomeResponse = HomeResponse(
            sections = listOf(mockSection)
        )
    }

    @Test
    fun `getHome returns valid response when api call is successful`() = runBlocking {
        // Given
        coEvery { mockApi.getHome() } returns Response.success(mockHomeResponse)

        // Act
        val actualResponse = dataSource.getHome()

        // Assert
        assertEquals(mockHomeResponse, actualResponse)
        coVerify { mockApi.getHome() }
    }

    @Test(expected = GenericException::class)
    fun `getHome throws GenericException when api call is unsuccessful`(): Unit = runBlocking {
        // Arrange
        val errorResponse = Response.error<HomeResponse>(404,
            "".toResponseBody("application/json".toMediaTypeOrNull())
        )
        coEvery { mockApi.getHome() } returns errorResponse

        // Act & Assert
        dataSource.getHome()
    }

}
