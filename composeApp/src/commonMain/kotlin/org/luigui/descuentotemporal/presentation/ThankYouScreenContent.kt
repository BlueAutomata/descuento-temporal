package org.luigui.descuentotemporal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.system.exitProcess

@Composable
fun ThankYouScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize(), // Use fillMaxSize to cover the entire screen
        contentAlignment = Alignment.Center // Center the content
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
            verticalArrangement = Arrangement.Center // Center vertically
        ) {
            // "Gracias por participar" text
            Text(
                text = "¡Gracias por participar!", // Updated text
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp), // Increased font size
                modifier = Modifier.padding(bottom = 16.dp) // Add some spacing below the text
            )

            // Button to close the program
            Button(onClick = { exitProcess(0) }) { // Close the program when clicked
                Text(
                    text = "Cerrar Programa", // Spanish translation for "Close Program"
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Increased font size
                )
            }
        }
    }
}