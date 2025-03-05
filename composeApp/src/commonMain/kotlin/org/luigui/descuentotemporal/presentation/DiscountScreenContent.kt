package org.luigui.descuentotemporal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.luigui.descuentotemporal.texts.TextContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun DiscountScreenContent(
    viewModel: DiscountViewModel, // <-- **ViewModel** is a key dependency
    onNavigateToThankYou: () -> Unit // <-- **Navigation callback** is critical for flow
) {
    // **State collection** using `collectAsState` for reactive updates
    val leftButtonValue by viewModel.leftButtonValue.collectAsState()
    val rightButtonValue by viewModel.rightButtonValue.collectAsState()
    val rightButtonWaitTime by viewModel.rightButtonWaitTime.collectAsState()
    val navigate by viewModel.navigateToThankYou.collectAsState()
    val currentBlock by viewModel.currentBlock.collectAsState()
    val showInstructions by viewModel.blockInstructions.collectAsState()
    val instructionStep by viewModel.instructionStep.collectAsState() // Observe instruction step from ViewModel
    val showButtons by viewModel.showButtons.collectAsState() // Observe button visibility state

    // **Currency formatting** for localization
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "CO")).apply {
        maximumFractionDigits = 0
        minimumFractionDigits = 0
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            if (showInstructions) {
                when (instructionStep) {
                    1 -> Text(
                        text = "EJEMPLO 1",
                        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
                        modifier = Modifier.padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    2 -> Text(
                        text = "EJEMPLO 2",
                        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
                        modifier = Modifier.padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    3 -> Text(
                        text = "DECISIONES DE SALUD Y DESCUENTO TEMPORAL EN PACIENTES CON PRE DIABETES Y DIABETES TIPO 2",
                        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
                        modifier = Modifier.padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }


                // **Instructions text** displayed conditionally
                Text(
                    text = formatTextWithStyles(TextContent.instructions[currentBlock] ?: ""),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp), // Increased font size
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // **Hide buttons when instructionStep == 3**
                if (instructionStep != 3) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth() // Ensure the Row takes the full width
                    ) {
                        // Left button for immediate reward
                        Button(
                            onClick = {
                                if (instructionStep == 1) {
                                    viewModel.updateInstructionStep(2) // Move to the next step
                                }
                            },
                            modifier = Modifier
                                .weight(1f) // Distribute available space equally
                                .padding(end = 8.dp) // Add spacing between buttons
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("Ganar ${currencyFormat.format(viewModel.updateExampleValue(block_num = currentBlock))} ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("ahora")
                                    }
                                },
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                                textAlign = TextAlign.Center // Center-align the text
                            )
                        }

                        // Right button for delayed reward
                        Button(
                            onClick = {
                                if (instructionStep == 2) {
                                    viewModel.updateInstructionStep(3) // Move to the next step
                                }
                            },
                            modifier = Modifier
                                .weight(1f) // Distribute available space equally
                                .padding(start = 8.dp) // Add spacing between buttons
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("Ganar ${currencyFormat.format(rightButtonValue)} ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("después")
                                    }
                                    append(" de $rightButtonWaitTime")
                                },
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                                textAlign = TextAlign.Center // Center-align the text
                            )
                        }
                    }
                }

                // **Dynamic instruction text based on step**
                when (instructionStep) {
                    1 -> Text(
                        text = "Por favor, presiona el botón de ganar ahora.",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    2 -> Text(
                        text = "Ahora, presiona el botón de ganar después.",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    3 -> Text(
                        text = "¡Bien hecho! Presiona Continuar para seguir.",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            } else {
                if (showButtons) {
                    Text(
                        text = TextContent.DiscountQuestion,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp), // Increased font size
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth() // Ensure the Row takes the full width
                    ) {
                        // Left button for immediate reward
                        Button(
                            onClick = {
                                if (navigate) {
                                    onNavigateToThankYou()
                                } else {
                                    viewModel.onLeftButtonClick()
                                    viewModel.toggleButtonsVisibility() // Hide buttons after click
                                }
                            },
                            modifier = Modifier
                                .weight(1f) // Distribute available space equally
                                .padding(end = 8.dp) // Add spacing between buttons
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("Ganar ${currencyFormat.format(leftButtonValue)} ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("ahora")
                                    }
                                },
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                                textAlign = TextAlign.Center // Center-align the text
                            )
                        }

                        // Right button for delayed reward
                        Button(
                            onClick = {
                                if (navigate) {
                                    onNavigateToThankYou()
                                } else {
                                    viewModel.onRightButtonClick()
                                    viewModel.toggleButtonsVisibility() // Hide buttons after click
                                }
                            },
                            modifier = Modifier
                                .weight(1f) // Distribute available space equally
                                .padding(start = 8.dp) // Add spacing between buttons
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    append("Ganar ${currencyFormat.format(rightButtonValue)} ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("después")
                                    }
                                    append(" de $rightButtonWaitTime")
                                },
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                                textAlign = TextAlign.Center // Center-align the text
                            )
                        }
                    }
                }
            }
        }

        // **Continuar Button** (shown only in step 3 of instructions)
        if (showInstructions && instructionStep == 3) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd // Align to bottom right
            ) {
                Button(
                    onClick = {
                        // Reset instruction step and move to the next block
                        viewModel.updateInstructionStep(1)
                        viewModel.dismissInstructions()
                        viewModel.resetButtonsVisibility() // Reset button visibility when instructions are dismissed
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = TextContent.NextButtonText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                    )
                }
            }
        }

        // **Next Button** (shown when buttons are hidden and showInstructions is false)
        if (!showButtons && !showInstructions) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "¡Respuesta guardada!",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Center // Center-align the text
                )
                Text(
                    text = "Para continuar da click en siguiente.",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Center // Center-align the text
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd // Align to bottom right
            ) {
                Button(
                    onClick = {
                        viewModel.toggleButtonsVisibility() // Show buttons again
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = TextContent.NextButtonText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                    )
                }
            }
        }
    }
}

fun formatTextWithStyles(text: String): AnnotatedString {
    val annotatedString = AnnotatedString.Builder()
    val boldRegex = Regex("\\*\\*(.*?)\\*\\*") // Matches text between **
    val underlineRegex = Regex("_(.*?)_") // Matches text between _
    val allMatches = (boldRegex.findAll(text) + underlineRegex.findAll(text))
        .sortedBy { it.range.first } // Sort matches by their start position

    var lastIndex = 0
    for (match in allMatches) {
        // Ensure the match range is within the bounds of the text
        if (match.range.first < lastIndex) {
            // Skip overlapping matches or invalid ranges
            continue
        }

        // Add non-styled text before the match
        if (match.range.first > lastIndex) {
            annotatedString.append(text.substring(lastIndex, match.range.first))
        }

        // Apply the appropriate style based on the match
        when {
            match.value.startsWith("**") -> {
                // Recursively process nested formatting inside bold text
                val innerText = match.groupValues[1]
                annotatedString.withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(formatTextWithStyles(innerText))
                }
            }
            match.value.startsWith("_") -> {
                // Apply underline style
                annotatedString.withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(match.groupValues[1])
                }
            }
        }
        lastIndex = match.range.last + 1
    }

    // Add remaining non-styled text
    if (lastIndex < text.length) {
        annotatedString.append(text.substring(lastIndex))
    }

    return annotatedString.toAnnotatedString()
}
