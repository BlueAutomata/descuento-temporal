package org.luigui.descuentotemporal

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.luigui.descuentotemporal.screens.HomeScreen
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

@Composable
@Preview
fun App() {
    val viewModel = remember { DiscountViewModel() }
    Navigator(HomeScreen(viewModel))
}