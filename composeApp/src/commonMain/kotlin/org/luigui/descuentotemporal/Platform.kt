package org.luigui.descuentotemporal

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform