package com.erictoader.compose.cartographer.extensions

import androidx.navigation.NavBackStackEntry
import com.erictoader.compose.cartographer.utils.namedArgumentOf

/**
 * Unpacks a previously bundled argument and returns it. If not found, it will throw a NullPointerException.
 * For a null-safe alternative see [NavBackStackEntry.getArgNullable]
 * @throws NullPointerException
 * @see NavBackStackEntry.getArgNullable
 */
inline fun <reified Arg> NavBackStackEntry.getArg(named: String? = null): Arg {
    val key = if (named == null) {
        arguments?.keySet()?.first { it.contains(Arg::class.simpleName ?: "") }
    } else namedArgumentOf(Arg::class.simpleName, named)

    return arguments?.getString(key)?.deserialize(Arg::class.java)!!
}

/**
 * Unpacks a previously bundled argument and returns it. If not found, it returns null.
 */
inline fun <reified Arg> NavBackStackEntry.getArgNullable(named: String? = null): Arg? {
    val key = if (named == null) {
        arguments?.keySet()?.first { it.contains(Arg::class.simpleName ?: "") }
    } else namedArgumentOf(Arg::class.simpleName, named)

    return arguments?.getString(key)?.deserialize(Arg::class.java)
}
