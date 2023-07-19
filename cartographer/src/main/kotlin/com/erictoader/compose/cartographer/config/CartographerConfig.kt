package com.erictoader.compose.cartographer.config

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Config class for Cartographer object serialization.
 * Any configuration should be done before starting to pass objects between screens.
 */
object CartographerConfig {

    private val jsonAdapters: MutableList<JsonAdapter<*>> = mutableListOf()
    private val jsonAdapterFactories: MutableList<JsonAdapter.Factory> = mutableListOf(
        KotlinJsonAdapterFactory()
    )

    internal val moshi: Moshi
        get() = Moshi.Builder()
            .apply {
                jsonAdapters.forEach { add(it) }
                jsonAdapterFactories.forEach { add(it) }
            }
            .build()

    /**
     * Adds a [JsonAdapter] to the Cartographer [Moshi] serializer
     */
    fun addJsonAdapter(jsonAdapter: JsonAdapter<*>) =
        jsonAdapters.add(jsonAdapter)

    /**
     * Adds a [JsonAdapter.Factory] to the Cartographer [Moshi] serializer
     */
    fun addJsonAdapterFactory(jsonAdapterFactory: JsonAdapter.Factory) =
        jsonAdapterFactories.add(jsonAdapterFactory)
}
