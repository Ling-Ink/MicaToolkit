package com.moling.micatoolkit.presentation.utils

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moling.micatoolkit.presentation.DetailRoute
import com.moling.micatoolkit.presentation.model.DetailItem
import dadb.Dadb

// region /* Device Info */

fun Dadb.getDeviceBrand(titleId: Int): DetailItem {
    return DetailItem(Icons.Outlined.Watch, titleId, this.execShell("getprop ro.product.brand").output, null)
}

fun Dadb.getDeviceModel(titleId: Int): DetailItem {
    return DetailItem(Icons.Outlined.Info, titleId, this.execShell("getprop ro.product.model").output, null)
}

fun Dadb.getDeviceAndroidId(titleId: Int): DetailItem {
    return DetailItem(Icons.Outlined.Android, titleId, this.execShell("settings get secure android_id").output, null)
}

fun Dadb.getDeviceAndroidBuild(titleId: Int): DetailItem {
    return DetailItem(Icons.Outlined.Android, titleId, this.execShell("getprop ro.build.version.release").output, null)
}

fun Dadb.getDeviceAndroidSDKVersion(titleId: Int): DetailItem {
    return DetailItem(Icons.Outlined.DeveloperMode, titleId, this.execShell("getprop ro.build.version.sdk").output, null)
}

fun Dadb.getDeviceAndroidSecPatch(titleId: Int): DetailItem {
    return DetailItem(Icons.Outlined.Security, titleId, this.execShell("getprop ro.build.version.security_patch").output, null)
}

// endregion

// region /* Screen Info */

fun Dadb.getScreenSize(title1Id: Int, title2Id: Int): List<DetailItem> {
    val execResult = this.execShell("wm size").output.split("\n")
    Log.d("MICA", execResult.toString())
    val ret = mutableListOf<DetailItem>()
    if (execResult.size == 1) {
        ret.add(DetailItem(Icons.Outlined.AspectRatio, title1Id, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(Icons.Outlined.Edit, title2Id, execResult[0].split(": ")[1], DetailRoute.DETAIL_SCREEN_SIZE))
        return ret
    } else if (execResult.size == 2) {
        ret.add(DetailItem(Icons.Outlined.AspectRatio, title1Id, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(Icons.Outlined.Edit, title2Id, execResult[1].split(": ")[1], DetailRoute.DETAIL_SCREEN_SIZE))
        return ret
    }
    return ret
}

fun Dadb.getScreenDensity(title1Id: Int, title2Id: Int): List<DetailItem> {
    val execResult = this.execShell("wm density").output.split("\n")
    val ret = mutableListOf<DetailItem>()
    if (execResult.size == 1) {
        ret.add(DetailItem(Icons.Outlined.BrandingWatermark, title1Id, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(Icons.Outlined.Edit, title2Id, execResult[0].split(": ")[1], DetailRoute.DETAIL_SCREEN_DENSITY))
        return ret
    } else if (execResult.size == 2) {
        ret.add(DetailItem(Icons.Outlined.BrandingWatermark, title1Id, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(Icons.Outlined.Edit, title2Id, execResult[1].split(": ")[1], DetailRoute.DETAIL_SCREEN_DENSITY))
        return ret
    }
    return ret
}

// endregion
