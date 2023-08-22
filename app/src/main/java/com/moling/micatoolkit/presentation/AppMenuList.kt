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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.utils.execShell
import com.moling.micatoolkit.presentation.widgets.func.FunctionItem

fun AdbExec(command: String): String {
    return requireNotNull(Constants.adb).execShell(command).output
}

fun FUNC_TOOLS(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.PermDeviceInformation,
            titleId = R.string.menu_deviceInfo,
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${ToolsRoute.TOOL_DEVICE}/${R.string.menu_deviceInfo}") },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.FitScreen,
            titleId = R.string.menu_screenInfo,
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${ToolsRoute.TOOL_SCREEN}/${R.string.menu_screenInfo}") },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.Folder,
            titleId = R.string.menu_fileList,
            onClick = {
                requireNotNull(it).navigate("${ToolsRoute.TOOL_FILE}/${FileSource.SOURCE_REMOTE}/${Constants.NULL_PARAM_PLACEHOLDER}/${FileFunc.FUNC_TYPE_BROWSE}")
            },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.GridView,
            titleId = R.string.menu_appManager,
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${ToolsRoute.TOOL_APPS}/${R.string.menu_appManager}") },
            onLongClick = { /* IGNORE */ }
        ),
    )
}

fun FUNC_DEVICE(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.Watch,
            titleId = R.string.detail_device_brand,
            subTitle = AdbExec("getprop ro.product.brand"),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.Info,
            titleId = R.string.detail_device_model,
            subTitle = AdbExec("getprop ro.product.model"),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.Android,
            titleId = R.string.detail_device_androidID,
            subTitle = AdbExec("settings get secure android_id"),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.Android,
            titleId = R.string.detail_device_build,
            subTitle = AdbExec("getprop ro.build.version.release"),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.DeveloperMode,
            titleId = R.string.detail_device_sdk,
            subTitle = AdbExec("getprop ro.build.version.sdk"),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.Security,
            titleId = R.string.detail_device_patch,
            subTitle = AdbExec("getprop ro.build.version.security_patch"),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
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
            subTitle = wm_size[0].split(": ")[1],
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_size_external,
            subTitle = wm_size[0].split(": ")[1],
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_SIZE}") },
            onLongClick = { /* IGNORE */ })
        )
    } else if (wm_size.size == 2) {
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_size_default,
            subTitle = wm_size[0].split(": ")[1],
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_size_external,
            subTitle = wm_size[1].split(": ")[1],
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_SIZE}") },
            onLongClick = { /* IGNORE */ })
        )
    }
    if (wm_density.size == 1) {
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_density_default,
            subTitle = wm_density[0].split(": ")[1],
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_density_external,
            subTitle = wm_density[0].split(": ")[1],
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_DENSITY}") },
            onLongClick = { /* IGNORE */ })
        )
    } else if (wm_density.size == 2) {
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_density_default,
            subTitle = wm_density[0].split(": ")[1],
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_density_external,
            subTitle = wm_density[1].split(": ")[1],
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_SCREEN_DENSITY}") },
            onLongClick = { /* IGNORE */ })
        )
    }
    return ret
}

fun FUNC_APPMGR(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.GridView,
            titleId = R.string.detail_appList,
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_APP_LIST}/${R.string.detail_appList}") },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.InstallMobile,
            titleId = R.string.detail_appInstall,
            onClick = { requireNotNull(it).navigate("${AppNavRoute.ROUTE_DETAIL}/${DetailRoute.DETAIL_APP_INSTALL}/${R.string.detail_appInstall}") },
            onLongClick = { /* IGNORE */ }
        ),
    )
}

fun DETAIL_APP_LIST(): List<FunctionItem> {
    val packages = AdbExec("pm list packages -3").split("\n")
    return packages.map {
        FunctionItem(
            icon = Icons.Outlined.Android,
            title = it.replace("package:", ""),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        )
    }
}

fun DETAIL_FILE_INFO(icon: ImageVector): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = icon,
            background = Color.Transparent,
            onClick = {},
            onLongClick = {}
        )
    )
}


