package com.genxsol.list.presentation.state

import androidx.compose.runtime.Immutable
import com.genxsol.list.domain.model.ListData

@Immutable
data class ListUIState(
    val listData: ListData?,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)