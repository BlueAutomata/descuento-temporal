package org.luigui.descuentotemporal

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    // Set a fixed window size
    val windowState = rememberWindowState(width = 1024.dp, height = 600.dp)

    Window(
        onCloseRequest = ::exitApplication,
        title = "DescuentoTemporal",
        state = windowState,
        resizable = false
    ) {
        App()
    }
}