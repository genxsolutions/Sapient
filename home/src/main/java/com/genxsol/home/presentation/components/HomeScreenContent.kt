package com.genxsol.home.presentation.components

import androidx.compose.runtime.Composable
import com.genxsol.home.domain.model.HomeSections
import com.genxsol.home.presentation.uievent.HomeUIEvent

@Composable
fun HomeScreenContent(homeData: HomeSections, onEvent: (HomeUIEvent) -> Unit) {
    SectionList(homeData.sections, onEvent)
}