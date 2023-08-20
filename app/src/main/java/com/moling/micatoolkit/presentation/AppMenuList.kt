package com.moling.micatoolkit.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FitScreen
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.PermDeviceInformation
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.DetailItem
import com.moling.micatoolkit.presentation.model.FunctionItem
import com.moling.micatoolkit.presentation.model.MenuItem
import com.moling.micatoolkit.presentation.utils.getDeviceAndroidBuild
import com.moling.micatoolkit.presentation.utils.getDeviceAndroidId
import com.moling.micatoolkit.presentation.utils.getDeviceAndroidSDKVersion
import com.moling.micatoolkit.presentation.utils.getDeviceAndroidSecPatch
import com.moling.micatoolkit.presentation.utils.getDeviceBrand
import com.moling.micatoolkit.presentation.utils.getDeviceModel
import com.moling.micatoolkit.presentation.utils.getScreenDensity
import com.moling.micatoolkit.presentation.utils.getScreenSize

fun FUNC_TOOLS(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.PermDeviceInformation,
            titleId = R.string.menu_deviceInfo,
            onClickRoute = ToolsRoute.TOOL_DEVICE
        ),
        FunctionItem(
            icon = Icons.Outlined.FitScreen,
            titleId = R.string.menu_screenInfo,
            onClickRoute = ToolsRoute.TOOL_SCREEN
        ),
        FunctionItem(
            icon = Icons.Outlined.Folder,
            titleId = R.string.menu_fileList,
            onClickRoute = ToolsRoute.TOOL_FILE
        ),
        FunctionItem(
            icon = Icons.Outlined.GridView,
            titleId = R.string.menu_appManager,
            onClickRoute = ToolsRoute.TOOL_APPS
        ),
    )
}

fun DETAIL_DEVICE(): List<DetailItem> {
    return listOf(
        requireNotNull(Constants.adb).getDeviceBrand(R.string.detail_device_brand),
        requireNotNull(Constants.adb).getDeviceModel(R.string.detail_device_model),
        requireNotNull(Constants.adb).getDeviceAndroidId(R.string.detail_device_androidID),
        requireNotNull(Constants.adb).getDeviceAndroidBuild(R.string.detail_device_build),
        requireNotNull(Constants.adb).getDeviceAndroidSDKVersion(R.string.detail_device_sdk),
        requireNotNull(Constants.adb).getDeviceAndroidSecPatch(R.string.detail_device_patch),
    )
}

fun DETAIL_SCREEN(): List<DetailItem> {
    return requireNotNull(Constants.adb).getScreenSize(R.string.detail_screen_size_default, R.string.detail_screen_size_external) +
            requireNotNull(Constants.adb).getScreenDensity(R.string.detail_screen_density_default, R.string.detail_screen_density_external)
}
