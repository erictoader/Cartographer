package com.erictoader.compose.cartographer

interface NavigationArgument {

    val argName: String
        get() = this::class.simpleName ?: ""
}
