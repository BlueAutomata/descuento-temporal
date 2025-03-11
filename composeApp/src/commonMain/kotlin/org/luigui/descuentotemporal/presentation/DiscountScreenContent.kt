package org.luigui.descuentotemporal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.luigui.descuentotemporal.texts.TextContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
private fun collectUiState(viewModel: DiscountViewModel): DiscountUiState {
    return DiscountUiState(
        leftButtonValue = viewModel.leftButtonValue.collectAsState().value,
        rightButtonValue = viewModel.rightButtonValue.collectAsState().value,
        rightButtonWaitTime = viewModel.rightButtonWaitTime.collectAsState().value,
        navigate = viewModel.navigateToThankYou.collectAsState().value,
        currentBlock = viewModel.currentBlock.collectAsState().value,
        showInstructions = viewModel.blockInstructions.collectAsState().value,
        instructionStep = viewModel.instructionStep.collectAsState().value,
        showButtons = viewModel.showButtons.collectAsState().value
    )
}

@Composable
fun DiscountScreenContent(
    viewModel: DiscountViewModel,
    onNavigateToThankYou: () -> Unit
) {
    // Collect state from ViewModel
    val uiState = collectUiState(viewModel)

    // State for UI elements
    var showNextButton by remember { mutableStateOf(false) }
    var showLoading by remember { mutableStateOf(false) }
    val showContinuarButton = viewModel.showContinueButton.collectAsState().value
    var lastPressedButton by remember { mutableStateOf<ButtonType?>(null) }

    // Currency formatting
    val currencyFormat = rememberCurrencyFormat()

    // Handle loading and next button visibility
    LaunchedEffect(uiState.showButtons) {
        if (!uiState.showButtons && !uiState.showInstructions) {
            showLoading = true // Show loading spinner
            delay(1500) // Delay for 1 second
            showLoading = false // Hide loading spinner
            showNextButton = true // Show Next button
        } else {
            showNextButton = false
            showLoading = false
        }
    }

    // Handle button click and delay
    LaunchedEffect(showLoading) {
        if (showLoading) {
            delay(1000) // Delay for 1 second
            showLoading = false // Hide loading spinner
            if (uiState.instructionStep == 3 && showContinuarButton) {
                viewModel.showInstructions()
            }
        }
    }

    // Main UI layout
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            if (!showLoading) {
                if (uiState.showInstructions) { // Conditionally render instructions
                    renderInstructions(uiState, viewModel, currencyFormat, lastPressedButton) { buttonType ->
                        lastPressedButton = buttonType // Track which button was pressed
                        if (isCorrectButtonPressed(uiState.instructionStep, buttonType)) {
                            showLoading = true // Show loading spinner
                        }
                    }
                } else if (!showContinuarButton) {
                    renderDiscountQuestion(uiState, viewModel, onNavigateToThankYou, currencyFormat)
                }
            }
        }

        // Render the loading spinner on top of the UI
        if (showLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Render other buttons only if loading is not active
        if (!showLoading) {
            if (showNextButton) {
                renderNextButton(uiState, showNextButton, viewModel)
            } else if (showContinuarButton) {
                renderContinueButton(uiState, viewModel)
            }
        }
    }
}

// Enum to track which button was pressed
enum class ButtonType {
    WIN_NOW,
    WIN_LATER
}

// Helper function to check if the correct button was pressed
private fun isCorrectButtonPressed(instructionStep: Int, buttonType: ButtonType?): Boolean {
    return when (instructionStep) {
        1 -> buttonType == ButtonType.WIN_NOW // Step 1: Correct button is "Win Now"
        2 -> buttonType == ButtonType.WIN_LATER // Step 2: Correct button is "Win Later"
        else -> false // No correct button for other steps
    }
}

@Composable
private fun renderInstructions(
    uiState: DiscountUiState,
    viewModel: DiscountViewModel,
    currencyFormat: NumberFormat,
    lastPressedButton: ButtonType?, // Track which button was pressed
    onButtonClick: (ButtonType) -> Unit // Callback for button click
) {
    when (uiState.instructionStep) {
        1 -> renderInstructionText("EJEMPLO 1")
        2 -> renderInstructionText("EJEMPLO 2")
        3 -> renderInstructionText("DECISIONES DE SALUD Y DESCUENTO TEMPORAL EN PACIENTES CON PRE DIABETES Y DIABETES TIPO 2")
    }

    if (uiState.currentBlock > 1) {
        renderChangeSituationText()
    }

    Text(
        text = formatTextWithStyles(TextContent.instructions[uiState.currentBlock] ?: ""),
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
        modifier = Modifier.padding(bottom = 16.dp)
    )

    // Show instruction buttons for steps 1 and 2
    if (uiState.instructionStep in 1..2) {
        renderInstructionButtons(uiState, viewModel, currencyFormat, onButtonClick)
    }

    renderDynamicInstructionText(uiState.instructionStep)
}

@Composable
private fun renderInstructionButtons(
    uiState: DiscountUiState,
    viewModel: DiscountViewModel,
    currencyFormat: NumberFormat,
    onButtonClick: (ButtonType) -> Unit // Callback for button click
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                onButtonClick(ButtonType.WIN_NOW) // Trigger the callback with button type
                if (uiState.instructionStep == 1) {
                    viewModel.updateInstructionStep(2) // Move to step 2
                    viewModel.dismissInstructions()
                    viewModel.showContinueButton()
                }
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Ganar ${currencyFormat.format(viewModel.updateExampleValue(block_num = uiState.currentBlock))} ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("ahora")
                    }
                },
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = {
                onButtonClick(ButtonType.WIN_LATER) // Trigger the callback with button type
                if (uiState.instructionStep == 2) {
                    viewModel.updateInstructionStep(3) // Move to step 3
                    viewModel.dismissInstructions()
                    viewModel.showContinueButton()
                }
            },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Ganar ${currencyFormat.format(uiState.rightButtonValue)} ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("después")
                    }
                    append(" de ${uiState.rightButtonWaitTime}")
                },
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                textAlign = TextAlign.Center
            )
        }
    }
}




@Composable
private fun rememberCurrencyFormat(): NumberFormat {
    return remember {
        NumberFormat.getCurrencyInstance(Locale("es", "CO")).apply {
            maximumFractionDigits = 0
            minimumFractionDigits = 0
        }
    }
}


@Composable
private fun renderInstructionText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp),
        modifier = Modifier.padding(vertical = 8.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun renderChangeSituationText() {
    Text(
        text = "CAMBO DE SITUACIÓN",
        style = MaterialTheme.typography.headlineMedium.copy(
            fontSize = 28.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(vertical = 8.dp),
        textAlign = TextAlign.Center
    )
    Text(
        text = "Por favor lee atentamente las siguientes instrucciones:",
        style = MaterialTheme.typography.headlineSmall.copy(
            fontSize = 26.sp,
            color = Color.Red
        ),
        modifier = Modifier.padding(vertical = 8.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun renderDynamicInstructionText(instructionStep: Int) {
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
}

@Composable
private fun renderDiscountQuestion(
    uiState: DiscountUiState,
    viewModel: DiscountViewModel,
    onNavigateToThankYou: () -> Unit,
    currencyFormat: NumberFormat
) {
    if (uiState.showButtons) {
        Text(
            text = TextContent.DiscountQuestion,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (uiState.navigate) {
                        onNavigateToThankYou()
                    } else {
                        viewModel.onLeftButtonClick()
                        viewModel.toggleButtonsVisibility()
                        print(uiState.toString())
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Ganar ${currencyFormat.format(uiState.leftButtonValue)} ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("ahora")
                        }
                    },
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = {
                    if (uiState.navigate) {
                        onNavigateToThankYou()
                    } else {
                        viewModel.onRightButtonClick()
                        viewModel.toggleButtonsVisibility()
                        print(uiState.toString())
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Ganar ${currencyFormat.format(uiState.rightButtonValue)} ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("después")
                        }
                        append(" de ${uiState.rightButtonWaitTime}")
                    },
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun renderContinueButton(uiState: DiscountUiState, viewModel: DiscountViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        if (uiState.instructionStep != 3) {
            Text(
                text = "¡Respuesta guardada!",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Para continuar da click en siguiente.",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = {
                if (uiState.instructionStep == 3) {
                    viewModel.updateInstructionStep(1) // Reset to step 1
                    viewModel.dismissInstructions() // Hide instructions
                    viewModel.resetButtonsVisibility() // Reset button visibility
                    viewModel.dismissContinueButton()
                }
                else {
                    viewModel.showInstructions()
                    viewModel.dismissContinueButton()
                }
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

@Composable
private fun renderNextButton(uiState: DiscountUiState, showNextButton: Boolean, viewModel: DiscountViewModel) {
    if (!uiState.showButtons && !uiState.showInstructions && showNextButton) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "¡Respuesta guardada!",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Para continuar da click en siguiente.",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = {
                    viewModel.toggleButtonsVisibility()
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

data class DiscountUiState(
    val leftButtonValue: Double,
    val rightButtonValue: Double,
    val rightButtonWaitTime: String,
    val navigate: Boolean,
    val currentBlock: Int,
    val showInstructions: Boolean,
    val instructionStep: Int,
    val showButtons: Boolean
)

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
