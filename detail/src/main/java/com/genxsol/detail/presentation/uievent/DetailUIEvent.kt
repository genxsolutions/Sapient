package com.genxsol.detail.presentation.uievent

sealed class DetailUIEvent {
    data object Dismiss : DetailUIEvent()
    data object LoadItemDetail : DetailUIEvent()
    data object SearchDetailClick: DetailUIEvent()

}