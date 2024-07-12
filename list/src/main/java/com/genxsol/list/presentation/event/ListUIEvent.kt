package com.genxsol.list.presentation.event

sealed interface ListUIEvent {
    data object Dismiss : ListUIEvent
    data object GetList: ListUIEvent
}