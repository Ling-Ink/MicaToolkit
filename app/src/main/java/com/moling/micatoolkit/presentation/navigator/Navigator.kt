package com.moling.micatoolkit.presentation.navigator

import androidx.navigation.NavController
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global

fun navToInfoAct(navController: NavController) {
    navController.navigate(NavRoute.ROUTE_INFO) {
        popUpTo(NavRoute.ROUTE_START)
    }
}

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
    global.set("detailType", NavRoute.DETAIL_DEVICE)
    global.set("detailTitleId", R.string.tool_deviceInfo)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

// region /* Screen Info */
fun navToScreenDetail(navController: NavController) {
    global.set("detailType", NavRoute.DETAIL_SCREEN)
    global.set("detailTitleId", R.string.tool_screenInfo)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToScreenSizeEditor(navController: NavController) {
    global.set("screenEditor", Constants.EDITOR_SCREEN_SIZE)
    navController.navigate(NavRoute.EDITOR_SCREEN) {
        popUpTo(NavRoute.ROUTE_DETAIL)
    }
}

fun navToScreenDensityEditor(navController: NavController) {
    global.set("screenEditor", Constants.EDITOR_SCREEN_DENSITY)
    navController.navigate(NavRoute.EDITOR_SCREEN) {
        popUpTo(NavRoute.ROUTE_DETAIL)
    }
}
// endregion

// region /* App Manager */
fun navToAppManager(navController: NavController) {
    global.set("detailType", NavRoute.DETAIL_APPMGR)
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

fun navToAppPackageInfo(navController: NavController, packageName: String) {
    global.set("detailType", NavRoute.DETAIL_APP_INFO)
    global.set("detailTitleId", R.string.detail_appInfo)
    global.set("packageName", packageName)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToAppUninstall(navController: NavController) {
    navController.navigate(NavRoute.UTIL_APP_UNINSTALL) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToAppInstall(navController: NavController) {
    navController.navigate(NavRoute.UTIL_APP_INSTALL) {
        popUpTo(NavRoute.ROUTE_DETAIL)
    }
}
// endregion

// region /* File Info */
fun navToFileManager(navController: NavController) {
    global.set("fileSource", Constants.FILE_SOURCE_REMOTE)
    global.set("fileBrowserMode", Constants.BROWSER_MODE_BROWSE)
    navController.navigate(NavRoute.ROUTE_FILE) {
        popUpTo(NavRoute.ROUTE_TOOL)
    }
}

fun navToFileSelector(navController: NavController) {
    global.set("fileSource", Constants.FILE_SOURCE_LOCAL)
    global.set("fileBrowserMode", Constants.BROWSER_MODE_FILE)
    navController.navigate(NavRoute.ROUTE_FILE) {
        popUpTo(NavRoute.UTIL_FILE_UPLOAD)
    }
}

fun navToFileUploadFromTarget(navController: NavController, uploadTarget: String) {
    global.set("fileUploadTarget", uploadTarget)
    navController.navigate(NavRoute.UTIL_FILE_UPLOAD) {
        popUpTo(NavRoute.ROUTE_FILE)
    }
}

fun navToFileInfo(navController: NavController, path: String, type: Int) {
    global.set("detailType", NavRoute.DETAIL_FILE_INFO)
    global.set("detailTitleId", R.string.detail_fileInfo)
    global.set("filePath", path)
    global.set("fileType", type)
    navController.navigate(NavRoute.ROUTE_DETAIL) {
        popUpTo(NavRoute.ROUTE_FILE)
    }
}

fun navToFileDownload(navController: NavController, downloadSource: String) {
    global.set("fileDownloadSource", downloadSource)
    navController.navigate(NavRoute.UTIL_FILE_DOWNLOAD) {
        popUpTo(NavRoute.ROUTE_DETAIL)
    }
}

fun navToDirSelector(navController: NavController) {
    global.set("fileSource", Constants.FILE_SOURCE_LOCAL)
    global.set("fileBrowserMode", Constants.BROWSER_MODE_DIRECTORY)
    navController.navigate(NavRoute.ROUTE_FILE) {
        popUpTo(NavRoute.UTIL_FILE_DOWNLOAD)
    }
}

fun navToFileDownloadFromTarget(navController: NavController, downloadTarget: String) {
    global.set("fileSource", Constants.FILE_SOURCE_REMOTE)
    global.set("fileDownloadTarget", downloadTarget)
    navController.navigate(NavRoute.UTIL_FILE_DOWNLOAD) {
        popUpTo(NavRoute.ROUTE_DETAIL)
    }
}

fun navToFolderCreate(navController: NavController, location: String) {
    global.set("folderCreateLocation", location)
    navController.navigate(NavRoute.UTIL_FOLDER_CREATE) {
        popUpTo(NavRoute.ROUTE_FILE)
    }
}
// endregion