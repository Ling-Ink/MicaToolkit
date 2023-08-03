package com.moling.micatoolkit.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
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
        composable(AppNavRoute.ROUTE_HELP) {
            HelpAct(navController)
        }
        composable(
            "${AppNavRoute.ROUTE_PORT}/{${AppNavParam.PARAM_HOST}}",
            arguments = listOf(
                navArgument(AppNavParam.PARAM_HOST) { type = NavType.StringType }
            )
        ) {
            val args = requireNotNull(it.arguments)
            val host = requireNotNull(args.getString(AppNavParam.PARAM_HOST))
            PortAct(navController, host)
        }
        composable(
            "${AppNavRoute.ROUTE_TOOL}/{${AppNavParam.PARAM_HOST}}/{${AppNavParam.PARAM_PORT}}",
            arguments = listOf(
                navArgument(AppNavParam.PARAM_HOST) { type = NavType.StringType },
                navArgument(AppNavParam.PARAM_PORT) { type = NavType.IntType }
            )
        ) {
            val args = requireNotNull(it.arguments)
            val host = requireNotNull(args.getString(AppNavParam.PARAM_HOST))
            val port = requireNotNull(args.getInt(AppNavParam.PARAM_PORT))
            ToolAct(navController, host, port)
        }
        composable(
            "${AppNavRoute.ROUTE_DETAIL}/{${AppNavParam.PARAM_TYPE}}",
            arguments = listOf(
                navArgument(AppNavParam.PARAM_TYPE) { type = NavType.StringType }
            )
        ) {
            val args = requireNotNull(it.arguments)
            val type = requireNotNull(args.getString(AppNavParam.PARAM_TYPE))
            DetailAct(type)
        }
    }
}

object AppNavRoute {
    const val ROUTE_MAIN = "main"
    const val ROUTE_TARGET = "target"
    const val ROUTE_PORT = "port"
    const val ROUTE_HELP = "help"
    const val ROUTE_TOOL = "tool"
    const val ROUTE_DETAIL = "detail"
}

object AppNavParam {
    const val PARAM_HOST = "host"
    const val PARAM_PORT = "port"
    const val PARAM_TYPE = "type"
}

object ToolsRoute {
    const val TOOL_DEVICE = "deviceInfo"
    const val TOOL_SCREEN = "screenInfo"
    const val TOOL_FILE = "fileExplorer"
}

object DetailRoute {
    const val DETAIL_SCREEN_SIZE = "screenSize"
    const val DETAIL_SCREEN_DENSITY = "screenDensity"
}