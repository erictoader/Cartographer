package com.erictoader.compose.cartographer

import androidx.navigation.NavController
import kotlin.reflect.full.superclasses

fun NavController.navigate(destination: Destination) = navigate(destination.route)

fun <Arg : NavigationArgument> NavController.navigate(
    destination: Destination,
    vararg args: Arg
) {
    var route = destination.route

    for (arg in args) {
        val argRoute = String(route.toCharArray())
        val serializedArg = arg.serialize(arg::class.java as Class<Arg>)

        val argSuperclasses = arg::class.superclasses
        if (argSuperclasses.isEmpty()) continue
        var argSuperclassesIndex = 0

        while (route == argRoute) {
            route = route.replace(
                oldValue = "{${argSuperclasses[argSuperclassesIndex].simpleName}}",
                newValue = serializedArg
            )
            argSuperclassesIndex++
            if (argSuperclasses.size == argSuperclassesIndex) break
        }
    }

    navigate(route = route)
}
