package com.moling.micatoolkit.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.moling.micatoolkit.presentation.activities.functionActs.detailAct.DetailAct
import com.moling.micatoolkit.presentation.activities.functionActs.PortAct
import com.moling.micatoolkit.presentation.activities.functionActs.StartAct
import com.moling.micatoolkit.presentation.activities.functionActs.TargetAct
import com.moling.micatoolkit.presentation.activities.functionActs.ToolAct
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.FilesAct

@Composable
fun NavHost() {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(navController = navController, startDestination = NavRoute.ROUTE_START) {
        composable(NavRoute.ROUTE_START) { StartAct(navController = navController) }
        composable(NavRoute.ROUTE_TARGET) { TargetAct(navController = navController) }
        composable(NavRoute.ROUTE_PORT) { PortAct(navController = navController) }

        composable(NavRoute.ROUTE_TOOL) { ToolAct(navController = navController) }
        composable(NavRoute.ROUTE_DETAIL) { DetailAct(navController = navController) }

        composable(NavRoute.ROUTE_FILE) { FilesAct(navController = navController) }
    }
}

object NavRoute {
    const val ROUTE_START = "start"
    const val ROUTE_TARGET = "target"
    const val ROUTE_PORT = "port"
    const val ROUTE_HELP = "help"

    const val ROUTE_TOOL = "tool"
    const val ROUTE_DETAIL = "detail"

    const val ROUTE_FILE = "file"
    const val TOOL_DEVICE = "device"
    const val TOOL_SCREEN = "screen"
    const val TOOL_APPMGR = "appmgr"

    const val DETAIL_APP_LIST = "appList"
}