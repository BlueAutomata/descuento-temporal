package org.luigui.descuentotemporal.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.luigui.descuentotemporal.presentation.QuestionnaireScreenContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

class QuestionnaireScreen(private val viewModel: DiscountViewModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        QuestionnaireScreenContent {
            navigator?.push(InstructionScreen(viewModel))
        }
    }
}