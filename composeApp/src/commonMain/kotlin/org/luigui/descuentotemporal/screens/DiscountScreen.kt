package org.luigui.descuentotemporal.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.luigui.descuentotemporal.presentation.DiscountScreenContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

class DiscountScreen(private val viewModel: DiscountViewModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        DiscountScreenContent(viewModel) {
            navigator?.push(ThankYouScreen())
        }
    }
}