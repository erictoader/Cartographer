package com.erictoader.compose.cartographer.extensions

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import com.erictoader.compose.cartographer.destination.Destination
import kotlin.reflect.KClass

/**
 * Cartographer NavGraphBuilder.navigation: Construct a nested [NavGraph] using [Destination] classes
 *
 * @param startDestinationClass the class of the start destination
 * @param routeClass the destination's unique destination route referenced by its class
 * @param arguments list of arguments to associate with destination
 * @param deepLinks list of deep links to associate with the destinations
 * @param builder the builder used to construct the graph
 */
fun <D1 : Destination, D2 : Destination> NavGraphBuilder.navigation(
    startDestinationClass: KClass<D1>,
    routeClass: KClass<D2>,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    builder: NavGraphBuilder.() -> Unit
) {
    addDestination(
        NavGraphBuilder(
            provider,
            Destination.routeOf(startDestinationClass),
            Destination.routeOf(routeClass)
        ).apply(builder).build().apply {
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

/**
 * Cartographer NavGraphBuilder.composable: Add the [Composable] to the [NavGraphBuilder] using [Destination] classes
 *
 * @param destinationClass destination route class of the composable
 * @param arguments list of arguments to associate with destination
 * @param deepLinks list of deep links to associate with the destinations
 * @param content composable for the destination
 */
fun <D : Destination> NavGraphBuilder.composable(
    destinationClass: KClass<D>,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            this.route = Destination.routeOf(destinationClass)
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}