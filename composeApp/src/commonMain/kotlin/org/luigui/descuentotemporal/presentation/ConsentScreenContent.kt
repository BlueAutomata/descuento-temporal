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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.luigui.descuentotemporal.texts.TextContent

@Composable
fun ConsentScreenContent(
    onNavigateToQuestionnaire: () -> Unit
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
                text = TextContent.consentHeading,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            // Additional text
            Text(
                text = TextContent.consentDataProtectionHeading,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentDataProtection,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            // Additional text
            Text(
                text = TextContent.consentIntroductionHeading,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentIntroduction,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentStudyPurposeHeading,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentStudyPurpose,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentDescriptionOfTheProcedureHeading,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentDescriptionOfTheProcedure,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentBenefitsHeadings,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            Text(
                text = TextContent.consentBenefits,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp) // Add some vertical spacing
            )

            // Spacer to push buttons to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // Row for Accept and Decline buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // Space buttons evenly
            ) {
                // Decline button in Spanish
                Button(
                    onClick = { /* Handle decline action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red) // Optional: Style for decline
                ) {
                    Text(text = "Rechazar") // "Decline" in Spanish
                }

                // Accept button in Spanish
                Button(
                    onClick = onNavigateToQuestionnaire
                ) {
                    Text(text = "Aceptar") // "Accept" in Spanish
                }
            }
        }
    }
}


