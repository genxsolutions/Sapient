package com.genxsol.home.presentation

import androidx.lifecycle.viewModelScope
import com.genxsol.core.navigation.NavigationService
import com.genxsol.core.presentation.StateAndEventViewModel
import com.genxsol.home.domain.model.ProductItem
import com.genxsol.home.domain.usecase.GetInitialHomeUseCase
import com.genxsol.home.presentation.state.HomeUIState
import com.genxsol.home.presentation.uievent.HomeUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getInitialHomeUseCase: GetInitialHomeUseCase,
    private val navigator: NavigationService
) : StateAndEventViewModel<HomeUIState, HomeUIEvent>(HomeUIState()) {
    override suspend fun handleEvent(event: HomeUIEvent) {
        when (event) {
            HomeUIEvent.LoadInitialHome -> {
                getInitialHome()
            }

            is HomeUIEvent.OnBannerClicked -> {
                onBannerClicked()
            }

            is HomeUIEvent.OnProductClicked -> {
                onProductClicked()
            }

            is HomeUIEvent.OnVerticalProductClicked -> {
                onVerticalProductClicked(event.item)
            }

            is HomeUIEvent.Dismiss -> {
                handleBack()
            }
        }
    }

    private fun getInitialHome() {
        viewModelScope.launch {
            getInitialHomeUseCase.getInitialHome()
                .onStart {
                    updateUiState { copy(isLoading = true) }
                }
                .catch { error ->
                    updateUiState { copy(error = error) }
                }
                .collect { homeSections ->
                    updateUiState {
                        copy(
                            homeData = homeSections,
                            isLoading = false,
                            selectedProductItem = null,
                            error = null
                        )
                    }
                }
        }
    }

    private fun onBannerClicked() {
        navigator.navigateTo("list")
    }

    private fun onVerticalProductClicked(productItem: ProductItem) {
        updateUiState {
            copy(selectedProductItem = productItem, isLoading = false)
        }
    }

    /* Route with arguments
      private fun onProductClicked(isSheetOpen: Boolean) {
          navigator.navigateTo( "detail/$isSheetOpen") {
              launchSingleTop = true
              restoreState = true
          }
      }
       */

    // Route with Detail Graph
    private fun onProductClicked() {
        navigator.navigateTo("detailgraph") {
            launchSingleTop = true
            restoreState = true
        }
    }


    private fun handleBack() {
        navigator.goBack()
    }

}