package com.erictoader.compose.cartographer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.erictoader.compose.cartographer.extensions.NavHost
import com.erictoader.compose.cartographer.extensions.composable
import com.erictoader.compose.cartographer.extensions.getArg
import com.erictoader.compose.cartographer.extensions.navigation
import com.erictoader.compose.cartographer.ui.screen.DetailsScreen
import com.erictoader.compose.cartographer.ui.screen.HomeScreen
import com.erictoader.compose.cartographer.ui.theme.ComposeCartographerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCartographerTheme {
                Navigator()
            }
        }
    }
}

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Main::class
    ) {
        mainGraph(navController)
    }
}

private fun NavGraphBuilder.mainGraph(navController: NavHostController) {
    navigation(
        routeClass = Main::class,
        startDestinationClass = Main.Home::class,
    ) {
        composable(Main.Home::class) {
            HomeScreen(
                navController = navController
            )
        }
        composable(Main.Details::class) { backStackEntry ->
            val asset1 = backStackEntry.getArg<Asset>(named = "primary")
            val asset2 = backStackEntry.getArg<Asset>(named = "secondary")

            DetailsScreen(
                navController = navController,
                primaryAsset = asset1,
                secondaryAsset = asset2
            )
        }
    }
}
