package com.erictoader.compose.cartographer

abstract class Destination(
    open val name: String,
    private val argumentPaths: Array<out String>
) {

    val route: String
        get() = name + argumentPaths.joinToString("/")

    init {
        if (argumentPaths != null) {
            val duplicates = argumentPaths
                .groupingBy { it }
                .eachCount()
                .filter { it.value > 1 }

            if (duplicates.isNotEmpty()) {
                throw IllegalStateException(
                    "Only one argument type allowed. Duplicate type: ${duplicates.keys.first()}"
                )
            }
        }
    }
}
