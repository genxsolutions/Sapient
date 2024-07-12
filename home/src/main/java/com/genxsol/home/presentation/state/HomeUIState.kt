package com.genxsol.home.presentation.state

import androidx.compose.runtime.Immutable
import com.genxsol.home.domain.model.HomeSections
import com.genxsol.home.domain.model.ProductItem

@Immutable
data class HomeUIState( // can be sealed class
    val isLoading: Boolean = false,
    val homeData: HomeSections? = null,
    val error: Throwable? = null,
    val selectedProductItem: ProductItem? = null
)