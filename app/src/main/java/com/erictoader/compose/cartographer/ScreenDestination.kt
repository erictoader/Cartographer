package com.erictoader.compose.cartographer

import com.erictoader.compose.cartographer.annotations.NamedArgument
import com.erictoader.compose.cartographer.destination.Destination

object Main : Destination() {
    object Home : Destination()
    data class Details(
        @NamedArgument("primary")
        val primaryAsset: Asset,
        @NamedArgument("secondary")
        val secondaryAsset: Asset
    ) : Destination()
}
