package com.genxsol.detail.presentation.state

import androidx.compose.runtime.Immutable
import com.genxsol.detail.domain.model.ItemDetail

@Immutable
data class DetailUIState(
    val itemData: ItemDetail?,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)