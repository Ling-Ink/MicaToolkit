package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.DeveloperMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Watch
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.utils.adbExec
import com.moling.micatoolkit.presentation.widgets.functionList.FunctionItem

fun deviceInfoList(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.Watch, titleId = R.string.detail_device_brand, subTitle = adbExec("getprop ro.product.brand"),
            onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }),
        FunctionItem(
            icon = Icons.Outlined.Info, titleId = R.string.detail_device_model, subTitle = adbExec("getprop ro.product.model"),
            onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }),
        FunctionItem(
            icon = Icons.Outlined.Android, titleId = R.string.detail_device_androidID, subTitle = adbExec("settings get secure android_id"),
            onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }),
        FunctionItem(
            icon = Icons.Outlined.Android, titleId = R.string.detail_device_build, subTitle = adbExec("getprop ro.build.version.release"),
            onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }),
        FunctionItem(
            icon = Icons.Outlined.DeveloperMode, titleId = R.string.detail_device_sdk, subTitle = adbExec("getprop ro.build.version.sdk"),
            onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }),
        FunctionItem(
            icon = Icons.Outlined.Security, titleId = R.string.detail_device_patch, subTitle = adbExec("getprop ro.build.version.security_patch"),
            onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }),
    )
}