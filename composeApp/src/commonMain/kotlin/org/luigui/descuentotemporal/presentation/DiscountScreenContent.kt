package org.luigui.descuentotemporal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.luigui.descuentotemporal.texts.TextContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun DiscountScreenContent(
    viewModel: DiscountViewModel,
    onNavigateToThankYou: () -> Unit
) {
    val leftButtonValue by viewModel.leftButtonValue.collectAsState()
    val rightButtonValue by viewModel.rightButtonValue.collectAsState()
    val rightButtonWaitTime by viewModel.rightButtonWaitTime.collectAsState()
    val navigate by viewModel.navigateToThankYou.collectAsState()

    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "CO"))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            // Discount question text
            Text(
                text = TextContent.DiscountQuestion,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Buttons row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Button 1
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        if (navigate) {
                            onNavigateToThankYou()
                        }
                        else {
                            viewModel.onLeftButtonClick()
                        }
                    }) {
                        Text(text = "Ganar ${currencyFormat.format(leftButtonValue)} ahora")
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Button 2
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        if (navigate) {
                            onNavigateToThankYou()
                        }
                        else {
                            viewModel.onRightButtonClick()
                        }
                    }) {
                        Text(text = "Ganar ${currencyFormat.format(rightButtonValue)} después de $rightButtonWaitTime")
                    }
                }
            }
        }
    }
}