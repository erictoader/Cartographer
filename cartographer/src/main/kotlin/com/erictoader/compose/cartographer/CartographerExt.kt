package com.erictoader.compose.cartographer

import androidx.navigation.NavBackStackEntry

inline fun <reified Arg : NavigationArgument> argumentOf(): String = "{${Arg::class.simpleName}}"

inline fun <reified Arg : NavigationArgument> NavBackStackEntry.getArgument(): Arg? =
    arguments?.getString(Arg::class.simpleName)?.deserialize(Arg::class.java)
