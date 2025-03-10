package org.luigui.descuentotemporal.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.luigui.descuentotemporal.presentation.HomeScreenContent
import org.luigui.descuentotemporal.viewmodels.DiscountViewModel
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView

class HomeScreen(private val viewModel: DiscountViewModel) : Screen {
    @Composable
    override fun Content() {
  // Use the updated ViewModel
        val navigator = LocalNavigator.current

        // Handle folder selection
        val onSelectFolder = {
            // Create a JFileChooser instance
            val fileChooser = JFileChooser(FileSystemView.getFileSystemView())
            fileChooser.dialogTitle = "Select Folder"
            fileChooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY // Allow only folders

            // Set the default directory to the user's Documents folder
            val documentsFolder = File(System.getProperty("user.home"), "Documents")
            fileChooser.currentDirectory = documentsFolder

            // Show the folder picker dialog
            val result = fileChooser.showOpenDialog(null)
            if (result == JFileChooser.APPROVE_OPTION) {
                // Update the ViewModel with the selected folder path
                val selectedFolder = fileChooser.selectedFile
                viewModel.updateSelectedFolderPath(selectedFolder.absolutePath)
            }
        }

        // Pass the ViewModel and navigation logic to HomeScreenContent
        HomeScreenContent(
            viewModel = viewModel,
            onNavigateToConsent = {
                navigator?.push(PersonalScreen(viewModel))
            },
            onSelectFolder = onSelectFolder
        )
    }
}