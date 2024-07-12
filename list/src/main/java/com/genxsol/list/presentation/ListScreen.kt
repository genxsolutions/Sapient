package com.genxsol.list.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.genxsol.core.components.ErrorComponent
import com.genxsol.core.components.LoadingComponent
import com.genxsol.list.presentation.components.ListContent
import com.genxsol.list.presentation.event.ListUIEvent

@Composable
fun ListScreen() {
    val viewModel: ListViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.onEvent(ListUIEvent.GetList)
    }

    when {
        state.isLoading -> { LoadingComponent() }
        state.error != null -> { ErrorComponent(error = state.error) }
        state.listData != null -> ListContent(state.listData!!.productList ?: emptyList())
    }

    BackHandler(enabled = true) {
        viewModel.onEvent(ListUIEvent.Dismiss)
    }
}