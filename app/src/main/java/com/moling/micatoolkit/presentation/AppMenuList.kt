package com.moling.micatoolkit.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.AspectRatio
import androidx.compose.material.icons.outlined.DeveloperMode
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FitScreen
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.InstallMobile
import androidx.compose.material.icons.outlined.PermDeviceInformation
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Watch
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.activities.functions.FunctionItem
import com.moling.micatoolkit.presentation.utils.execShell

fun AdbExec(command: String): String {
    return requireNotNull(Constants.adb).execShell(command).output
}

fun FUNC_TOOLS(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.PermDeviceInformation,
            titleId = R.string.menu_deviceInfo,
            onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${ToolsRoute.TOOL_DEVICE}/${R.string.menu_deviceInfo}"
        ),
        FunctionItem(
            icon = Icons.Outlined.FitScreen,
            titleId = R.string.menu_screenInfo,
            onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${ToolsRoute.TOOL_SCREEN}/${R.string.menu_screenInfo}"
        ),
        FunctionItem(
            icon = Icons.Outlined.Folder,
            titleId = R.string.menu_fileList,
            onClickRoute = "${ToolsRoute.TOOL_FILE}/${FileSource.SOURCE_REMOTE}/NULL"
        ),
        FunctionItem(
            icon = Icons.Outlined.GridView,
            titleId = R.string.menu_appManager,
            onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${ToolsRoute.TOOL_APPS}/${R.string.menu_appManager}"
        ),
    )
}

fun FUNC_DEVICE(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.Watch,
            titleId = R.string.detail_device_brand,
            subTitle = AdbExec("getprop ro.product.brand")
        ),
        FunctionItem(
            icon = Icons.Outlined.Info,
            titleId = R.string.detail_device_model,
            subTitle = AdbExec("getprop ro.product.model")
        ),
        FunctionItem(
            icon = Icons.Outlined.Android,
            titleId = R.string.detail_device_androidID,
            subTitle = AdbExec("settings get secure android_id")
        ),
        FunctionItem(
            icon = Icons.Outlined.Android,
            titleId = R.string.detail_device_build,
            subTitle = AdbExec("getprop ro.build.version.release")
        ),
        FunctionItem(
            icon = Icons.Outlined.DeveloperMode,
            titleId = R.string.detail_device_sdk,
            subTitle = AdbExec("getprop ro.build.version.sdk")
        ),
        FunctionItem(
            icon = Icons.Outlined.Security,
            titleId = R.string.detail_device_patch,
            subTitle = AdbExec("getprop ro.build.version.security_patch")
        ),
    )
}

fun FUNC_SCREEN(): List<FunctionItem> {
    val wm_size = AdbExec("wm size").split("\n")
    val wm_density = AdbExec("wm density").split("\n")
    val ret = mutableListOf<FunctionItem>()
    if (wm_size.size == 1) {
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_size_default,
            subTitle = wm_size[0].split(": ")[1])
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_size_external,
            subTitle = wm_size[0].split(": ")[1], onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_SIZE}/NULL")
        )
    } else if (wm_size.size == 2) {
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_size_default,
            subTitle = wm_size[0].split(": ")[1])
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_size_external,
            subTitle = wm_size[1].split(": ")[1], onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_SIZE}/NULL")
        )
    }
    if (wm_density.size == 1) {
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_density_default,
            subTitle = wm_density[0].split(": ")[1])
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_density_external,
            subTitle = wm_density[0].split(": ")[1], onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_DENSITY}/NULL")
        )
    } else if (wm_density.size == 2) {
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_density_default,
            subTitle = wm_density[0].split(": ")[1])
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_density_external,
            subTitle = wm_density[1].split(": ")[1], onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_DENSITY}/NULL")
        )
    }
    return ret
}

fun FUNC_APPMGR(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.GridView,
            titleId = R.string.detail_appList,
            onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_APP_LIST}/${R.string.detail_appList}"
        ),
        FunctionItem(
            icon = Icons.Outlined.InstallMobile,
            titleId = R.string.detail_appInstall,
            onClickRoute = "${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_APP_INSTALL}/${R.string.detail_appInstall}"
        ),
    )
}

fun DETAIL_APP_LIST(): List<FunctionItem> {
    val packages = AdbExec("pm list packages -3").split("\n")
    return packages.map {
        FunctionItem(
            icon = Icons.Outlined.Android,
            title = it.replace("package:", "")
        )
    }
}


