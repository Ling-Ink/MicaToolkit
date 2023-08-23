package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FitScreen
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.PermDeviceInformation
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.navigator.navToAppManager
import com.moling.micatoolkit.presentation.navigator.navToDeviceDetail
import com.moling.micatoolkit.presentation.navigator.navToFileManager
import com.moling.micatoolkit.presentation.navigator.navToScreenDetail
import com.moling.micatoolkit.presentation.widgets.functionList.FunctionItem

fun toolsList(): List<FunctionItem> {
    return listOf(
        FunctionItem(icon = Icons.Outlined.PermDeviceInformation, titleId = R.string.tool_deviceInfo,
            onClick = { navToDeviceDetail(requireNotNull(it)) }, onLongClick = { /* IGNORE */ }),
        FunctionItem(icon = Icons.Outlined.FitScreen, titleId = R.string.tool_screenInfo,
            onClick = { navToScreenDetail(requireNotNull(it)) }, onLongClick = { /* IGNORE */ }),
        FunctionItem(
            icon = Icons.Outlined.Folder, titleId = R.string.tool_fileList,
            onClick = { navToFileManager(requireNotNull(it)) }, onLongClick = { /* IGNORE */ }),
        FunctionItem(
            icon = Icons.Outlined.GridView, titleId = R.string.tool_appManager,
            onClick = { navToAppManager(requireNotNull(it)) }, onLongClick = { /* IGNORE */ }),
    )
}