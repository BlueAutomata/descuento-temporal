package org.luigui.descuentotemporal.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.luigui.descuentotemporal.data.Measurement
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DiscountViewModel : ViewModel() {

    private val MAX_NUMBER_OF_TRAILS = 20
    val ORIGINAL_VALUE = 2600000.0
    private val PREDEFINED_VALUES = listOf(
        2600000.00,
        2465460.00,
        2330348.00,
        2194725.00,
        2058648.00,
        1922172.00,
        1785346.00,
        1648216.00,
        1510826.00,
        1373212.00,
        1235407.00,
        1097438.00,
        959327.00,
        821090.00,
        682739.00,
        544280.00,
        405709.00,
        267022.00,
        145379.00,
        26000.00
    )

    private val WAITING_VALUES = listOf(
        "1 semana",
        "1 mes",
        "3 meses",
        "6 meses",
        "1 año",
        "6 años"
    )

    private val EXAMPLE_VALUES = listOf(
        1510826.00,
        821090.00,
        1097438.00,
        405709.00,
        1785346.00,
        682739.00
    )

    private val _currentMeasurement = mutableStateOf(Measurement())
    val currentMeasurement: Measurement get() = _currentMeasurement.value

    private val _navigateToThankYou = MutableStateFlow(false)
    val navigateToThankYou: StateFlow<Boolean> = _navigateToThankYou

    private val _leftButtonValue = MutableStateFlow(PREDEFINED_VALUES[0])
    val leftButtonValue: StateFlow<Double> = _leftButtonValue

    private val _rightButtonValue = MutableStateFlow(ORIGINAL_VALUE)
    val rightButtonValue: StateFlow<Double> = _rightButtonValue

    private val _rightButtonWaitTime = MutableStateFlow(WAITING_VALUES[0])
    val rightButtonWaitTime: StateFlow<String> = _rightButtonWaitTime

    private var _selectedFolderPath by mutableStateOf<String?>(getDefaultFolderPath())
    val selectedFolderPath: String? get() = _selectedFolderPath

    private val _completeName = MutableStateFlow("")
    val completeName: StateFlow<String> = _completeName

    private val _id = MutableStateFlow("")
    val id: StateFlow<String> = _id

    private val _currentBlock = MutableStateFlow(1)
    val currentBlock: StateFlow<Int> = _currentBlock

    private val _blockInstructions = MutableStateFlow(true)
    val blockInstructions: StateFlow<Boolean> = _blockInstructions

    // New state variable for instruction step
    private val _instructionStep = MutableStateFlow(1) // 1 = Press left, 2 = Press right, 3 = Show continue
    val instructionStep: StateFlow<Int> = _instructionStep.asStateFlow()

    // File to store the selected folder path
    private val userHome: String = System.getProperty("user.home")
    private val storageFile = File(userHome, "user_selected_folder.txt")

    init {
        // Load the last selected folder path when the ViewModel is created
        loadSelectedFolderPath()
    }

    fun updateSelectedFolderPath(path: String) {
        _selectedFolderPath = path
        saveSelectedFolderPath(path)
    }

    // Function to save the selected folder path to a file
    private fun saveSelectedFolderPath(path: String) {
        try {
            storageFile.writeText(path)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Function to load the selected folder path from a file
    private fun loadSelectedFolderPath() {
        try {
            if (storageFile.exists()) {
                _selectedFolderPath = storageFile.readText()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Function to check if a folder with the ID already exists
    fun doesFolderExist(): Boolean {
        // Replace this with your actual logic to check if the folder exists
        val sanitizedId = _id.value.toString().trim() ?: ""
        val sanitizedCompleteName = _completeName.value.trim().replace(" ", "_") ?: ""

        // Construct the folder name correctly
        val folderName = listOf(sanitizedId, sanitizedCompleteName).filter { it.isNotEmpty() }.joinToString("_")

        val folderPath = "$selectedFolderPath${File.separator}${folderName}"
        return File(folderPath).exists()
    }


    fun updateExampleValue(block_num: Int): Double {
        return EXAMPLE_VALUES[block_num -1]
    }

    // Function to update instruction step
    fun updateInstructionStep(step: Int) {
        _instructionStep.value = step
    }

    fun nextBlock() {
        _currentBlock.value += 1
    }

    fun updateCompleteName(newName: String) {
        _completeName.value = newName
    }

    fun updateId(newId: String) {
        _id.value = newId
    }

    fun dismissInstructions() {
        _blockInstructions.value = false
    }

    private fun getDefaultFolderPath(): String {
        return File(System.getProperty("user.home"), "Documents").absolutePath
    }

    fun onLeftButtonClick() {
        val current = _currentMeasurement.value

        // Old state
        val selectedState = current.copy(
            userName = _completeName.value,
            id = _id.value,
            block = current.block,
            side = "izquierda",
            trial = current.trial,
            value = _leftButtonValue.value,
            change = if (current.trial == 1) false else if (current.side == "derecha") true else false
        )

        var block = current.block
        var trial = current.trial
        if (trial == MAX_NUMBER_OF_TRAILS) {
            _leftButtonValue.value = PREDEFINED_VALUES[0]
            trial = 1
            block += 1
            nextBlock()
            if (block > WAITING_VALUES.size) {
                _navigateToThankYou.value = true
            } else {
                _rightButtonWaitTime.value = WAITING_VALUES[block - 1]
                _blockInstructions.value = true // Show block instructions
            }
        } else {
            if (current.trial < PREDEFINED_VALUES.size) { // Use size - 1 to avoid index out of bounds
                _leftButtonValue.value = PREDEFINED_VALUES[trial]
            }
            trial += 1
        }

        writeMeasurementToExcel(selectedState)

        _currentMeasurement.value = current.copy(
            userName = _completeName.value,
            id = _id.value,
            block = block,
            side = "izquierda",
            trial = trial,
            value = _leftButtonValue.value,
            change = current.side == "derecha"
        )
    }

    fun onRightButtonClick() {
        val current = _currentMeasurement.value

        // Old state
        val selectedState = current.copy(
            userName = _completeName.value,
            id = _id.value,
            block = current.block,
            side = "derecha",
            trial = current.trial,
            value = ORIGINAL_VALUE,
            change = if (current.trial == 1) false else if (current.side == "izquierda") true else false
        )

        var block = current.block
        var trial = current.trial
        if (trial == MAX_NUMBER_OF_TRAILS) {
            _leftButtonValue.value = PREDEFINED_VALUES[0]
            trial = 1
            block += 1
            nextBlock()
            if (block > WAITING_VALUES.size) {
                _navigateToThankYou.value = true
            } else {
                _rightButtonWaitTime.value = WAITING_VALUES[block - 1]
                _blockInstructions.value = true // Show block instructions
            }
        } else {
            if (current.trial < PREDEFINED_VALUES.size) { // Use size - 1 to avoid index out of bounds
                _leftButtonValue.value = PREDEFINED_VALUES[trial]
            }
            trial += 1
        }

        writeMeasurementToExcel(selectedState)

        // New State
        _currentMeasurement.value = current.copy(
            userName = _completeName.value,
            id = _id.value,
            block = block,
            side = "derecha",
            trial = trial,
            value = ORIGINAL_VALUE,
            change = current.side == "izquierda"
        )
    }

    // Function to reset navigation state
    fun resetNavigation() {
        _navigateToThankYou.value = false
    }

    private fun writeMeasurementToExcel(measurement: Measurement) {
        // Format current date and time
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentDateTime = LocalDateTime.now().format(dateTimeFormatter)

        // Ensure id and completeName are properly formatted
        val sanitizedId = _id.value.toString().trim() ?: ""
        val sanitizedCompleteName = _completeName.value.trim().replace(" ", "_") ?: ""

        // Construct the folder name correctly
        val folderName = listOf(sanitizedId, sanitizedCompleteName).filter { it.isNotEmpty() }.joinToString("_")

        // Construct directory and file paths
        //val directoryPath = "$selectedFolderPath${File.separator}$folderName"
        val directoryPath = "$selectedFolderPath${File.separator}${folderName}"
        val filePath = "$directoryPath${File.separator}data.xlsx"

        // Ensure the directory exists
        val directory = File(directoryPath)
        if (!directory.exists()) {
            directory.mkdirs() // Create directory and subdirectories if necessary
        }

        val file = File(filePath)
        val workbook: Workbook
        val sheet: Sheet

        if (file.exists()) {
            FileInputStream(file).use { fis ->
                workbook = WorkbookFactory.create(fis)
            }
            sheet = workbook.getSheetAt(0) ?: workbook.createSheet("Sheet1")
        } else {
            workbook = XSSFWorkbook()
            sheet = workbook.createSheet("Sheet1")

            // Create header row if needed
            val headerRow = sheet.createRow(0)
            headerRow.createCell(0).setCellValue("Fecha y Hora")
            headerRow.createCell(1).setCellValue("Cedula")
            headerRow.createCell(2).setCellValue("Nombre completo")
            headerRow.createCell(3).setCellValue("Bloque")
            headerRow.createCell(4).setCellValue("Ensayo")
            headerRow.createCell(5).setCellValue("Valor")
            headerRow.createCell(6).setCellValue("Lado")
            headerRow.createCell(7).setCellValue("Cambio")
        }

        // Determine the next row index (skip header if present)
        val lastRowNum = sheet.lastRowNum
        val newRowIndex = if (lastRowNum == 0 && sheet.getRow(0) == null) 0 else lastRowNum + 1

        // Create a new row and fill it with measurement data
        val newRow = sheet.createRow(newRowIndex)

        // Add the date and time to the first column
        newRow.createCell(0).setCellValue(currentDateTime)

        // Add the rest of the measurement data
        newRow.createCell(1).setCellValue(measurement.id)
        newRow.createCell(2).setCellValue(measurement.userName)
        newRow.createCell(3).setCellValue(measurement.block.toDouble())
        newRow.createCell(4).setCellValue(measurement.trial.toDouble())
        newRow.createCell(5).setCellValue(measurement.value.toDouble())
        newRow.createCell(6).setCellValue(measurement.side)
        newRow.createCell(7).setCellValue(measurement.change.toString())

        // Write the changes to the file
        FileOutputStream(file).use { fos ->
            workbook.write(fos)
        }
        workbook.close()
    }
}