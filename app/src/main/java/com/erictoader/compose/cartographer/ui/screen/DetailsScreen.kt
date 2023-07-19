package com.erictoader.compose.cartographer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.erictoader.compose.cartographer.Asset

@Composable
fun DetailsScreen(
    navController: NavController,
    primaryAsset: Asset,
    secondaryAsset: Asset
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = primaryAsset.id)
            Text(text = secondaryAsset.id)
        }
    }
}