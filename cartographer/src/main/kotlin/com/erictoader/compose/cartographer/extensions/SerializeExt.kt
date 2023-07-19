package com.erictoader.compose.cartographer.extensions

import com.erictoader.compose.cartographer.utils.jsonAdapterOf
import com.erictoader.compose.cartographer.utils.urlDecode
import com.erictoader.compose.cartographer.utils.urlEncode

fun <Arg> String.deserialize(jClass: Class<Arg>): Arg? =
    jsonAdapterOf(jClass).fromJson(this.urlDecode())

internal fun <Arg> Arg.serialize(jClass: Class<Arg>): String =
    jsonAdapterOf(jClass).toJson(this).urlEncode()
