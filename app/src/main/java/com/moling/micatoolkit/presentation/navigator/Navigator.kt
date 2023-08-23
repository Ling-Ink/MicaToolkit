package com.moling.micatoolkit.presentation.navigator

import androidx.navigation.NavController
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global

fun navToTargetAct(navController: NavController) {
    navController.navigate(NavRoute.ROUTE_TARGET) {
        popUpTo(NavRoute.ROUTE_START)
    }
}

fun navToPortAct(navController: NavController, host: String) {
    global.set("host", host)
    navController.navigate(NavRoute.ROUTE_PORT) {
        popUpTo(NavRoute.ROUTE_START)
    }
}

fun navToToolAct(navController: NavController, port: Int) {
    global.set("port", port)
    navController.navigate(NavRoute.ROUTE_TOOL) {
        popUpTo(NavRoute.ROUTE_START)
    }
}

fun navToDeviceDetail(navController: NavController) {
    global.set("detailType", NavRoute.TOOL_DEVICE)
    global.set("detailTitleId", R.string.tool_deviceInfo)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToScreenDetail(navController: NavController) {
    global.set("detailType", NavRoute.TOOL_SCREEN)
    global.set("detailTitleId", R.string.tool_screenInfo)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToAppManager(navController: NavController) {
    global.set("detailType", NavRoute.TOOL_APPMGR)
    global.set("detailTitleId", R.string.tool_appManager)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToAppListDetail(navController: NavController) {
    global.set("detailType", NavRoute.DETAIL_APP_LIST)
    global.set("detailTitleId", R.string.detail_appList)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToFileManager(navController: NavController) {
    global.set("fileSource", Constants.FILE_SOURCE_REMOTE)
    navController.navigate(NavRoute.ROUTE_FILE) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToFileSelector(navController: NavController) {
    global.set("fileSource", Constants.FILE_SOURCE_LOCAL)
    navController.navigate(NavRoute.ROUTE_FILE) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToScreenSizeEditor(navController: NavController) {
    global.set("screenEditor", Constants.EDITOR_SCREEN_SIZE)
    navController.navigate(NavRoute.EDITOR_SCREEN_SIZE) {
        popUpTo(NavRoute.ROUTE_DETAIL)
    }
}

fun navToScreenDensityEditor(navController: NavController) {
    global.set("screenEditor", Constants.EDITOR_SCREEN_DENSITY)
    navController.navigate(NavRoute.EDITOR_SCREEN_SIZE) {
        popUpTo(NavRoute.ROUTE_DETAIL)
    }
}

fun navToFileUploadFromTarget(navController: NavController, uploadTarget: String) {
    global.set("fileUploadTarget", uploadTarget)
    navController.navigate(NavRoute.UTIL_FILE_UPLOAD) {
        popUpTo(NavRoute.ROUTE_FILE)
    }
}

fun navToFileUploadFromSource(navController: NavController, uploadSource: String) {
    global.set("fileSource", Constants.FILE_SOURCE_REMOTE)
    global.set("fileUploadSource", uploadSource)
    navController.navigate(NavRoute.UTIL_FILE_UPLOAD) {
        popUpTo(NavRoute.ROUTE_FILE)
    }
}