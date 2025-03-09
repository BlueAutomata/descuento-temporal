package org.luigui.descuentotemporal.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.luigui.descuentotemporal.presentation.ExperimentStartScreenContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

class ExperimentStartScreen(private val viewModel: DiscountViewModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        ExperimentStartScreenContent {
            navigator?.push(DiscountScreen(viewModel))
        }
    }
}