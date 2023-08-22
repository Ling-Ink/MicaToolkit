package com.moling.micatoolkit.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.moling.micatoolkit.presentation.activities.functions.DetailAct
import com.moling.micatoolkit.presentation.activities.functions.FilesAct
import com.moling.micatoolkit.presentation.activities.HelpAct
import com.moling.micatoolkit.presentation.activities.MainAct
import com.moling.micatoolkit.presentation.activities.options.PortAct
import com.moling.micatoolkit.presentation.activities.options.TargetAct
import com.moling.micatoolkit.presentation.activities.functions.ToolAct
import com.moling.micatoolkit.presentation.activities.utils.UploadAct

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
            "${AppNavRoute.ROUTE_DETAIL}/{${AppNavParam.PARAM_TYPE}}/{${AppNavParam.PARAM_TITLE}}",
            arguments = listOf(
                navArgument(AppNavParam.PARAM_TYPE) { type = NavType.StringType },
                navArgument(AppNavParam.PARAM_TITLE) { type = NavType.IntType }
            )
        ) {
            val args = requireNotNull(it.arguments)
            val type = requireNotNull(args.getString(AppNavParam.PARAM_TYPE))
            val title = requireNotNull(args.getInt(AppNavParam.PARAM_TITLE))
            DetailAct(navController, type, title)
        }

        composable(
            "${ToolsRoute.TOOL_FILE}/{${AppNavParam.PARAM_SOURCE}}/{${AppNavParam.PARAM_PATH}}/{${AppNavParam.PARAM_FUNC}}",
            arguments = listOf(
                navArgument(AppNavParam.PARAM_SOURCE) { type = NavType.StringType },
                navArgument(AppNavParam.PARAM_PATH) { type = NavType.StringType },
                navArgument(AppNavParam.PARAM_FUNC) { type = NavType.StringType },
            )
        ) {
            val args = requireNotNull(it.arguments)
            val source = requireNotNull(args.getString(AppNavParam.PARAM_SOURCE))
            val path = requireNotNull(args.getString(AppNavParam.PARAM_PATH))
            val func = requireNotNull(args.getString(AppNavParam.PARAM_FUNC))
            if (path == Constants.NULL_PARAM_PLACEHOLDER) FilesAct(navController, source, func) else FilesAct(navController, source, func, path)
        }

        composable(
            "${UtilsRoute.UTIL_UPLOAD}/{${AppNavParam.PARAM_PATH}}",
            arguments = listOf(
                navArgument(AppNavParam.PARAM_PATH) { type = NavType.StringType }
            )
        ) {
            val args = requireNotNull(it.arguments)
            val path = requireNotNull(args.getString(AppNavParam.PARAM_PATH))
            UploadAct(navController, path)
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
    const val PARAM_TITLE = "title"
    const val PARAM_SOURCE = "source"
    const val PARAM_PATH = "path"
    const val PARAM_FUNC = "func"
}

object ToolsRoute {
    const val TOOL_DEVICE = "deviceInfo"
    const val TOOL_SCREEN = "screenInfo"
    const val TOOL_FILE = "fileExplorer"
    const val TOOL_APPS = "appManager"
}

object DetailRoute {
    const val DETAIL_SCREEN_SIZE = "screenSize"
    const val DETAIL_SCREEN_DENSITY = "screenDensity"
    const val DETAIL_APP_LIST = "appList"
    const val DETAIL_APP_INSTALL = "appInstall"
}

object UtilsRoute {
    const val UTIL_UPLOAD = "upload"
}

object FileSource {
    const val SOURCE_LOCAL = "local"
    const val SOURCE_REMOTE = "remote"
}

object FileFunc {
    const val FUNC_TYPE_BROWSE = "browse"
    const val FUNC_TYPE_UPLOAD = "upload"
}