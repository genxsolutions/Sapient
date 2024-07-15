package com.genxsol.sapiant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.genxsol.home.presentation.HomeScreen
import com.genxsol.list.presentation.ListScreen
import com.genxsol.navigation.AppNavigation
import com.genxsol.navigation.Navigator
import com.genxsol.sapiant.ui.theme.SapiantTestTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SapiantTestTheme {
                AppNavigation(
                    navigator = navigator,
                    homeScreen = {
                        HomeScreen()
                    },
                    listScreen = {
                        ListScreen()
                    }
                )
            }
        }
    }
}