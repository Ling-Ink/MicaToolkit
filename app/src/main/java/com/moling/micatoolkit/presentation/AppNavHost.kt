package com.moling.micatoolkit.presentation

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

@Composable
fun AppNavHost() {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(navController = navController, startDestination = AppNavRoute.ROUTE_MAIN) {
        composable(AppNavRoute.ROUTE_MAIN) {
            MainAct(navController)
        }
        composable(AppNavRoute.ROUTE_TARGET) {
            TargetAct(navController)
        }
    }
}

object AppNavRoute {
    const val ROUTE_MAIN = "main"
    const val ROUTE_TARGET = "target"
}