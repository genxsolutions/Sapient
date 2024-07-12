package com.genxsol.home.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.genxsol.home.domain.model.HomeSectionAdapterItem
import com.genxsol.home.presentation.sections.BannerSection
import com.genxsol.home.presentation.sections.SectionTitle
import com.genxsol.home.presentation.sections.SlidableSection
import com.genxsol.home.presentation.uievent.HomeUIEvent

@Composable
fun SectionList(sections: List<HomeSectionAdapterItem>?, onEvent: (HomeUIEvent) -> Unit) {
    sections?.let {
        LazyColumn {
            items(items = sections, key = { section ->
                when (section) {
                    is HomeSectionAdapterItem.Banner -> "Banner-" + section.bannerItem.joinToString("-") { it.navigationData }
                    is HomeSectionAdapterItem.SlidableProducts -> "Slidable-${section.id}"
                    is HomeSectionAdapterItem.VerticalProducts -> "Vertical-${section.sectionTitle}"
                }
            }) { section ->
                when (section) {
                    is HomeSectionAdapterItem.Banner -> BannerSection(section.bannerItem, onEvent)
                    is HomeSectionAdapterItem.SlidableProducts -> SlidableSection(section.productItem, section.sectionTitle, onEvent)
                    is HomeSectionAdapterItem.VerticalProducts -> {
                        VerticalSection(section, onEvent)
                    }
                }
            }
        }
    }
}

@Composable
fun VerticalSection(section: HomeSectionAdapterItem.VerticalProducts, onEvent: (HomeUIEvent) -> Unit) {
    SectionTitle(title = section.sectionTitle)
    val products = remember { section.productItem }
    products.forEach { productItem ->
        VerticalItemCard(
            item = productItem,
            onProductClick = { onEvent(HomeUIEvent.OnVerticalProductClicked(productItem)) }
        )
    }
}