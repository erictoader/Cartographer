package com.erictoader.compose.cartographer.extensions

import androidx.annotation.MainThread
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.createGraph
import com.erictoader.compose.cartographer.destination.Destination
import kotlin.reflect.KClass

/**
 * Cartographer NavHost: Android NavHost adapted to use [Destination] classes instead of string routes
 *
 * Provides in place in the Compose hierarchy for self contained navigation to occur.
 *
 * Once this is called, any Composable within the given [NavGraphBuilder] can be navigated to from
 * the provided [navController].
 *
 * The builder passed into this method is [remember]ed. This means that for this NavHost, the
 * contents of the builder cannot be changed.
 *
 * @param navController the navController for this host
 * @param startDestination the class of the start destination
 * @param modifier The modifier to be applied to the layout.
 * @param route the route for the graph
 * @param builder the builder used to construct the graph
 */
@Composable
fun <D : Destination> NavHost(
    navController: NavHostController,
    startDestination: KClass<D>,
    modifier: Modifier = Modifier,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit
) {
    androidx.navigation.compose.NavHost(
        navController,
        remember(route, Destination.routeOf(startDestination), builder) {
            navController.createGraph(Destination.routeOf(startDestination), route, builder)
        },
        modifier
    )
}

/**
 * Cartographer NavController.navigate: Uses [Destination] objects to navigate to screens
 *
 * Navigate to a route in the current NavGraph. If an invalid route is given, an
 * [IllegalArgumentException] will be thrown.
 *
 * @param destination destination object
 * @param navOptions special options for this navigation operation
 * @param navigatorExtras extras to pass to the [Navigator]
 *
 * @throws IllegalArgumentException if the given route is invalid
 */
@MainThread
@JvmOverloads
fun NavController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) = navigate(
    route = destination.argRoute,
    navOptions = navOptions,
    navigatorExtras = navigatorExtras
)
