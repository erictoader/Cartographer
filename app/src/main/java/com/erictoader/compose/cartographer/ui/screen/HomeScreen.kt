package com.erictoader.compose.cartographer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.erictoader.compose.cartographer.Asset
import com.erictoader.compose.cartographer.Main
import com.erictoader.compose.cartographer.extensions.navigate

@Composable
fun HomeScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            navController.navigate(
                Main.Details(
                    primaryAsset = Asset("1234567890"),
                    secondaryAsset = Asset("abcdefghij")
                )
            )
        }) {
            Text(text = "Click me")
        }
    }
}