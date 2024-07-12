package com.genxsol.detail.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.genxsol.core.components.ErrorComponent
import com.genxsol.core.components.LoadingComponent
import com.genxsol.detail.presentation.components.DetailContent
import com.genxsol.detail.presentation.uievent.DetailUIEvent

@Composable
fun DetailScreen() {
    val viewModel: DetailViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.onEvent(DetailUIEvent.LoadItemDetail)
    }

    when {
        state.isLoading -> { LoadingComponent() }
        state.error != null -> { ErrorComponent(error = state.error) }
        state.itemData != null -> { DetailContent(
            state.itemData!!,
            onSearchClicked = { viewModel.onEvent(DetailUIEvent.SearchDetailClick) }
        ) }
    }
}
