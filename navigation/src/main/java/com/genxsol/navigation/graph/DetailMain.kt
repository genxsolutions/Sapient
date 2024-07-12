package com.genxsol.navigation.screens.detailwithowngraph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.genxsol.navigation.utils.ArgsScreen
import com.genxsol.navigation.utils.DestinationRoute

object DetailMain: ArgsScreen<Unit> {
    override fun destination(arg: Unit): DestinationRoute = route

    override val route: String = "detail/main"
    override val arguments: List<NamedNavArgument> = emptyList()

    override fun objectParser(entry: NavBackStackEntry){}
}