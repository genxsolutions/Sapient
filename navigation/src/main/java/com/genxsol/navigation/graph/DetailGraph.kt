package com.genxsol.navigation.graph

import com.genxsol.navigation.screens.detailwithowngraph.DetailMain
import com.genxsol.navigation.screens.detailwithowngraph.DetailSearch
import com.genxsol.navigation.utils.NavigationGraph

object DetailGraph : NavigationGraph {
    override val route: String
        get() = "detailgraph"
    override val startDestination: String
        get() = detailMain.destination(Unit)

    val detailMain = DetailMain
    val detailSearch = DetailSearch
}