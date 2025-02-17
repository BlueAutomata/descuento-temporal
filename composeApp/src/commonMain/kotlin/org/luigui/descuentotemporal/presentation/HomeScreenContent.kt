package org.luigui.descuentotemporal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel

@Composable
fun HomeScreenContent(
    viewModel: DiscountViewModel,
    onNavigateToConsent: () -> Unit,
    onSelectFolder: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Welcome text in Spanish
            Text(
                text = "¡Bienvenido!",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Folder selection row (browse button + text field)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Text field to display the selected folder path
                TextField(
                    value = viewModel.selectedFolderPath ?: "",
                    onValueChange = { /* Read-only, so no action */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    label = { Text("Carpeta Seleccionada") }, // Updated to Spanish
                    readOnly = true // Make the text field read-only
                )

                // Button to browse and select folder (medium emphasis)
                Button(
                    onClick = onSelectFolder,
                    modifier = Modifier.padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer, // Medium emphasis
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text(text = "Examinar") // Updated to Spanish
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Next button in the bottom right corner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(
                    onClick = onNavigateToConsent
                ) {
                    Text(text = "Siguiente") // Updated to Spanish
                }
            }
        }
    }
}