package com.erictoader.compose.cartographer.utils

import com.erictoader.compose.cartographer.destination.Destination
import com.erictoader.compose.cartographer.annotations.NamedArgument
import com.erictoader.compose.cartographer.extensions.serialize
import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.jvmErasure

internal fun <Arg> argumentOf(property: KProperty<Arg>): String =
    "{" + namedArgumentOf(
        className = property.returnType.jvmErasure.simpleName,
        named = property.findAnnotation<NamedArgument>()?.name ?: property.name
    ) + "}"

internal fun <Arg> valueOf(property: KProperty<Arg>, owner: Destination): String =
    property.call(owner).serialize(property.returnType.jvmErasure.java as Class<Any?>)

fun namedArgumentOf(
    className: String?,
    named: String,
    separator: String = "-"
) = "$className$separator$named"
