package com.erictoader.compose.cartographer

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLDecoder
import java.net.URLEncoder

fun <Arg : NavigationArgument> String.deserialize(jClass: Class<Arg>): Arg? =
    jsonAdapterOf(jClass).fromJson(this.urlDecode())

internal fun <Arg : NavigationArgument> Arg.serialize(jClass: Class<Arg>): String =
    jsonAdapterOf(jClass).toJson(this).urlEncode()

private fun <Arg : NavigationArgument> jsonAdapterOf(jClass: Class<Arg>): JsonAdapter<Arg> =
    Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(jClass)

private fun String.urlEncode(): String = URLEncoder.encode(this, "utf-8")

private fun String.urlDecode(): String = URLDecoder.decode(this, "utf-8")
