package org.luigui.descuentotemporal.data

data class Measurement(
    val userName: String? = null,
    val id: String? = null,
    val block: Int = 1,          // bloque (ID or identifier for a block)
    var trial: Int = 1,          // ensayo (Name or type of trial/experiment)
    var side: String? = null,    // lado (Side or direction)
    val value: Double = 2600000.0,  // valor (Measured value)
    val change: Boolean = false  // cambio (Indicates if there was a change - true/false)
)