package com.genxsol.home.data.domainimpl.mapper

import com.genxsol.home.data.api.model.HomeResponse
import com.genxsol.home.data.api.model.HomeSection
import com.genxsol.home.domain.model.BannerItem
import com.genxsol.home.domain.model.HomeSectionAdapterItem
import com.genxsol.home.domain.model.HomeSections
import com.genxsol.home.domain.model.ProductItem

fun HomeResponse.mapToHomeSections(): HomeSections {
    val homeSectionsAdapterItems = mutableListOf<HomeSectionAdapterItem>()

    this.sections.forEach { section ->
        val viewType = when (section.type) {
            1 -> HomeSectionAdapterItem.VIEW_TYPE_BANNER
            2 -> HomeSectionAdapterItem.VIEW_TYPE_SLIDABLE_PRODUCTS
            4 -> HomeSectionAdapterItem.VIEW_TYPE_VERTICAL_PRODUCTS
            else -> -1
        }

        val sectionItem = when (viewType) {
            HomeSectionAdapterItem.VIEW_TYPE_BANNER -> HomeSectionAdapterItem.Banner(
                viewType = viewType,
                bannerItem = section.sectionData.map { banner ->
                    mapHomeSectionToBannerItem(banner)
                },
                id = section.id
            )

            HomeSectionAdapterItem.VIEW_TYPE_SLIDABLE_PRODUCTS -> HomeSectionAdapterItem.SlidableProducts(
                viewType = viewType,
                productItem = section.sectionData.map { product ->
                    mapToProductItem(product)
                },
                sectionTitle = section.sectionTitle ?: "",
                id = section.id
            )

            HomeSectionAdapterItem.VIEW_TYPE_VERTICAL_PRODUCTS -> HomeSectionAdapterItem.VerticalProducts(
                viewType = viewType,
                productItem = section.sectionData.map { product ->
                    mapToProductItem(product)
                },
                sectionTitle = section.sectionTitle ?: "",
                id = section.type
            )

            else -> null
        }

        sectionItem?.let { homeSectionsAdapterItems.add(it) }

    }
    return HomeSections(sections = homeSectionsAdapterItems)
}


private fun mapHomeSectionToBannerItem(homeSection: HomeSection): BannerItem {
    return BannerItem(
        image = homeSection.image,
        navigationData = homeSection.navigationData
    )
}


private fun mapToProductItem(response: HomeSection): ProductItem {
    return ProductItem(
        productId = response.productId,
        productImage = response.productImage,
        text = response.text,
        subText = response.subText,
        review = response.review,
        questions = response.questions,
        rating = response.rating,
        share = response.share,
        piece = response.piece,
        soldOutText = response.soldOutText
    )
}
