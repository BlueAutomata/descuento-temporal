package org.luigui.descuentotemporal.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.luigui.descuentotemporal.presentation.InstructionScreenContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

class InstructionScreen(private val viewModel: DiscountViewModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        InstructionScreenContent {
            navigator?.push(DiscountScreen(viewModel))
        }
    }
}