package com.genxsol.home.presentation.uievent

import com.genxsol.home.domain.model.ProductItem

sealed class HomeUIEvent {
    data object OnBannerClicked : HomeUIEvent()
    data object LoadInitialHome : HomeUIEvent()
    data object OnProductClicked : HomeUIEvent()
    data class OnVerticalProductClicked(val item: ProductItem) : HomeUIEvent()
    data object Dismiss : HomeUIEvent()
}