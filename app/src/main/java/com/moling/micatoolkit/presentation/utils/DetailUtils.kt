package com.moling.micatoolkit.presentation.utils

import android.util.Log
import com.moling.micatoolkit.presentation.DetailRoute
import com.moling.micatoolkit.presentation.model.DetailItem
import dadb.Dadb

// region /* Device Info */

fun Dadb.getDeviceBrand(title: String): DetailItem {
    return DetailItem(title, this.execShell("getprop ro.product.brand").output, null)
}

fun Dadb.getDeviceModel(title: String): DetailItem {
    return DetailItem(title, this.execShell("getprop ro.product.model").output, null)
}

fun Dadb.getDeviceAndroidId(title: String): DetailItem {
    return DetailItem(title, this.execShell("settings get secure android_id").output, null)
}

fun Dadb.getDeviceAndroidBuild(title: String): DetailItem {
    return DetailItem(title, this.execShell("getprop ro.build.version.release").output, null)
}

fun Dadb.getDeviceAndroidSDKVersion(title: String): DetailItem {
    return DetailItem(title, this.execShell("getprop ro.build.version.sdk").output, null)
}

fun Dadb.getDeviceAndroidSecPatch(title: String): DetailItem {
    return DetailItem(title, this.execShell("getprop ro.build.version.security_patch").output, null)
}

// endregion

// region /* Screen Info */

fun Dadb.getScreenSize(title1: String, title2: String): List<DetailItem> {
    val execResult = this.execShell("wm size").output.split("\n")
    Log.d("MICA", execResult.toString())
    val ret = mutableListOf<DetailItem>()
    if (execResult.size == 2) {
        ret.add(DetailItem(title1, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(title2, execResult[0].split(": ")[1], DetailRoute.DETAIL_SCREEN_SIZE))
        return ret
    } else if (execResult.size == 3) {
        ret.add(DetailItem(title1, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(title2, execResult[1].split(": ")[1], DetailRoute.DETAIL_SCREEN_SIZE))
        return ret
    }
    return ret
}

fun Dadb.getScreenDensity(title1: String, title2: String): List<DetailItem> {
    val execResult = this.execShell("wm density").output.split("\n")
    val ret = mutableListOf<DetailItem>()
    if (execResult.size == 2) {
        ret.add(DetailItem(title1, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(title2, execResult[0].split(": ")[1], DetailRoute.DETAIL_SCREEN_DENSITY))
        return ret
    } else if (execResult.size == 3) {
        ret.add(DetailItem(title1, execResult[0].split(": ")[1], null))
        ret.add(DetailItem(title2, execResult[1].split(": ")[1], DetailRoute.DETAIL_SCREEN_DENSITY))
        return ret
    }
    return ret
}

// endregion
