package com.genxsol.home.data.domainimpl.mapper
import com.genxsol.home.data.api.model.HomeResponse
import com.genxsol.home.data.api.model.HomeSection
import com.genxsol.home.data.api.model.Section
import com.genxsol.home.domain.model.*
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeResponseMapperTest {

    @Test
    fun `test mapToHomeSections with banner section`() {
        val sectionData = HomeSection(
            image = "image_url",
            productImage = ""
        )

        val section = Section(
            type = 1,
            id = 101,
            sectionData = listOf(sectionData)
        )

        val homeResponse = HomeResponse(
            sections = listOf(section)
        )

        val expectedHomeSections = HomeSections(
            sections = listOf(
                HomeSectionAdapterItem.Banner(
                    viewType = HomeSectionAdapterItem.VIEW_TYPE_BANNER,
                    bannerItem = listOf(
                        BannerItem(
                            image = "image_url",
                            navigationData = "ND_49581L"
                        )
                    ),
                    id = 101
                )
            )
        )

        val result = homeResponse.mapToHomeSections()
        assertEquals(expectedHomeSections, result)
    }

    @Test
    fun `test mapToHomeSections with slidable products section`() {
        val sectionData = HomeSection(
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
            sectionData = listOf(sectionData)
        )

        val homeResponse = HomeResponse(
            sections = listOf(section)
        )

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

        val result = homeResponse.mapToHomeSections()
        assertEquals(expectedHomeSections, result)
    }

    @Test
    fun `test mapToHomeSections with vertical products section`() {
        val sectionData = HomeSection(
            productId = "prod2",
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
            type = 4,
            id = 103,
            sectionTitle = "Vertical Products",
            sectionData = listOf(sectionData)
        )

        val homeResponse = HomeResponse(
            sections = listOf(section)
        )

        val expectedHomeSections = HomeSections(
            sections = listOf(
                HomeSectionAdapterItem.VerticalProducts(
                    viewType = HomeSectionAdapterItem.VIEW_TYPE_VERTICAL_PRODUCTS,
                    productItem = listOf(
                        ProductItem(
                            productId = "prod2",
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
                    sectionTitle = "Vertical Products",
                    id = 4
                )
            )
        )

        val result = homeResponse.mapToHomeSections()
        assertEquals(expectedHomeSections, result)
    }
}
