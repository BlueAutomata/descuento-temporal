package org.luigui.descuentotemporal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.luigui.descuentotemporal.texts.TextContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

@Composable
fun PersonalScreenContent(
    viewModel: DiscountViewModel,
    onNavigateToQuestionnaire: () -> Unit
) {
    // Observe state changes
    val completeName by viewModel.completeName.collectAsState()
    val id by viewModel.id.collectAsState()

    // Check if the form is valid
    val isFormValid = completeName.isNotBlank() && id.isNotBlank()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading
            Text(
                text = "Registro de datos",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp), // Increased font size
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Full name field
            TextField(
                value = completeName,
                onValueChange = { newText -> viewModel.updateCompleteName(newText) },
                label = {
                    Text(
                        text = "Nombre Completo",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Increased font size
                    )
                },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp) // Increased font size for input text
            )

            // Document number field (accepts only digits)
            TextField(
                value = id,
                onValueChange = { newText ->
                    // Filter out non-digit characters
                    val filteredText = newText.filter { it.isDigit() }
                    viewModel.updateId(filteredText)
                },
                label = {
                    Text(
                        text = "Número de documento",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Increased font size
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp) // Increased font size for input text
            )

            Spacer(modifier = Modifier.weight(1f))

            // Next Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onNavigateToQuestionnaire,
                    enabled = isFormValid // Button only enabled if fields are filled
                ) {
                    Text(
                        text = TextContent.NextButtonText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Increased font size
                    )
                }
            }
        }
    }
}


