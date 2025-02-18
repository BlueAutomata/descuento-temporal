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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.luigui.descuentotemporal.texts.TextContent

@Composable
fun InstructionScreenContent(
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
            // Consent heading
            Text(
                text = TextContent.InstructionsHeading,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp), // Increase font size
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            // Additional text
            Text(
                text = TextContent.Instructions,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp), // Increase font size
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            // Spacer to push buttons to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // Row for Accept and Decline buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End // Align button to the right
            ) {
                Button(onClick = onNavigateToDiscount) {
                    Text(
                        text = TextContent.NextButtonText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Increase button text size
                    )
                }
            }
        }
    }
}