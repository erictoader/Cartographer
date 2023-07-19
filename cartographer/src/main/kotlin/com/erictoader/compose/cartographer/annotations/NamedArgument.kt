package com.erictoader.compose.cartographer.annotations

/**
 * Used to give a specific name to a property value of a [Destination]. This makes it much easier to
 * use in code and makes the Destinations work regardless of property name changes
 */
@Target(AnnotationTarget.PROPERTY)
annotation class NamedArgument(val name: String)
