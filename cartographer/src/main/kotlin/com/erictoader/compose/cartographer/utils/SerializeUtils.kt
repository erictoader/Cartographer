package com.erictoader.compose.cartographer.utils

import com.erictoader.compose.cartographer.config.CartographerConfig
import com.squareup.moshi.JsonAdapter
import java.net.URLDecoder
import java.net.URLEncoder

internal fun <Arg> jsonAdapterOf(jClass: Class<Arg>): JsonAdapter<Arg> =
    CartographerConfig.moshi.adapter(jClass)

internal fun String.urlEncode(): String = URLEncoder.encode(this, "utf-8")

internal fun String.urlDecode(): String = URLDecoder.decode(this, "utf-8")