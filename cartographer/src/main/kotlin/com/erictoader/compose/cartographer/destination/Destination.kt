package com.erictoader.compose.cartographer.destination

import com.erictoader.compose.cartographer.annotations.ExcludedFromArguments
import com.erictoader.compose.cartographer.utils.argumentOf
import com.erictoader.compose.cartographer.utils.valueOf
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

/**
 * Parent class of all Cartographer Destinations
 * It automatically constructs routes and bundles values and is aware of all of its fields
 * Ignoring certain fields is done using [ExcludedFromArguments] annotation
 */
abstract class Destination {

    private val argumentValues: List<String> by lazy {
        this::class.memberProperties
            .filterNot { it.annotations.contains(ExcludedFromArguments()) }
            .map { valueOf(it, this) }
    }

    @ExcludedFromArguments
    internal val argRoute: String by lazy {
        this::class.simpleName + PATH_SEPARATOR + argumentValues.joinToString(PATH_SEPARATOR)
    }

    internal companion object {
        private const val PATH_SEPARATOR = "/"
        private val routeMap: MutableMap<KClass<out Destination>, String> = mutableMapOf()

        internal fun <D : Destination> routeOf(destinationClass: KClass<D>): String =
            routeMap.getOrPut(
                key = destinationClass,
                defaultValue = {
                    destinationClass.simpleName + PATH_SEPARATOR + destinationClass.memberProperties
                        .filterNot { it.annotations.contains(ExcludedFromArguments()) }
                        .joinToString(PATH_SEPARATOR) { argumentOf(it) }
                }
            )
    }
}
