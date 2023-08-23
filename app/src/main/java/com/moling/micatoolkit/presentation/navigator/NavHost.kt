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
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadFileAct
import com.moling.micatoolkit.presentation.activities.functionActs.editorAct.ScreenEditorAct
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.DownloadFileAct
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

        composable(NavRoute.EDITOR_SCREEN) { ScreenEditorAct(navController = navController) }

        composable(NavRoute.UTIL_FILE_UPLOAD) { UploadFileAct(navController = navController) }
        composable(NavRoute.UTIL_FILE_DOWNLOAD) { DownloadFileAct(navController = navController) }
    }
}

object NavRoute {
    // Pre-config Acts
    const val ROUTE_START = "_start"
    const val ROUTE_TARGET = "_target"
    const val ROUTE_PORT = "_port"
    const val ROUTE_HELP = "_help"
    // Function Acts
    const val ROUTE_TOOL = "tool"
    const val ROUTE_DETAIL = "detail"
    // ToolsAct
    const val ROUTE_FILE = "file"
    const val DETAIL_DEVICE = "device"
    const val DETAIL_SCREEN = "screen"
    const val DETAIL_APPMGR = "appmgr"
    // DetailAct - AppMgr
    const val DETAIL_APP_LIST = "appList"
    // DetailAct - Screen
    const val EDITOR_SCREEN = "_Screen"
    // FileAct
    const val UTIL_FILE_UPLOAD = "@fileUpload"
    const val UTIL_FILE_DOWNLOAD = "@fileDownload"
    const val DETAIL_FILE_INFO = "fileInfo"
}