package org.luigui.descuentotemporal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.luigui.descuentotemporal.texts.TextContent

@Composable
fun QuestionnaireScreenContent(
    onNavigateToDiscount: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
        ) {
            Text(
                text = "Questionnaire",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = "Please proceed to the questionnaire",
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            // TextField for user input
            var names by remember { mutableStateOf("") } // State to hold the text input
            TextField(
                value = names,
                onValueChange = { newText ->
                    names = newText // Update the state when the text changes
                },
                label = { Text("Enter your name") }, // Label for the text field
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth() // Make the text field take up the full width
            )

            var surnames by remember { mutableStateOf("") } // State to hold the text input
            TextField(
                value = surnames,
                onValueChange = { newText ->
                    surnames = newText // Update the state when the text changes
                },
                label = { Text("Enter your name") }, // Label for the text field
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth() // Make the text field take up the full width
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End // Align button to the right
            ) {
                Button(onClick = onNavigateToDiscount) {
                    Text(text = TextContent.NextButtonText)
                }
            }
        }
    }
}