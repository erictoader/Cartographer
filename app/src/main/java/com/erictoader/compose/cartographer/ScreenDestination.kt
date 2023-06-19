package com.erictoader.compose.cartographer

sealed class ScreenDestination(
    override val name: String,
    vararg argumentPaths: String
) : Destination(name, argumentPaths) {

    object Intro : ScreenDestination("intro") {
        object SplashScreen : ScreenDestination("splash")
        object AuthScreen : ScreenDestination("auth")
    }

    object Main : ScreenDestination("main") {
        object MoviesScreen : ScreenDestination("movies")
        object SeriesScreen : ScreenDestination("series")
        object SettingsScreen : ScreenDestination("settings")
        object DetailsScreen : ScreenDestination("details")
    }
}
