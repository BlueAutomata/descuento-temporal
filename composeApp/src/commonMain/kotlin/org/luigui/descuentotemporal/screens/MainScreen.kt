package org.luigui.descuentotemporal.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

class MainScreen : Screen {
    @Composable
    override fun Content() {
        // Create the shared ViewModel once here.
        val viewModel = remember { DiscountViewModel() }
        val navigator = LocalNavigator.current

        // Pass the ViewModel to HomeScreen
        HomeScreen(
            viewModel = viewModel
        )
    }
}
