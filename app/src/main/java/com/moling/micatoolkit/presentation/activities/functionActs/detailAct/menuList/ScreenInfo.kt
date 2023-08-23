package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AspectRatio
import androidx.compose.material.icons.outlined.BrandingWatermark
import androidx.compose.material.icons.outlined.Edit
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.navigator.navToScreenDensityEditor
import com.moling.micatoolkit.presentation.navigator.navToScreenDetail
import com.moling.micatoolkit.presentation.navigator.navToScreenSizeEditor
import com.moling.micatoolkit.presentation.utils.adbExec
import com.moling.micatoolkit.presentation.widgets.functionList.FunctionItem

fun screenInfoList(): List<FunctionItem> {
    val ret = mutableListOf<FunctionItem>()
    val wmSize = adbExec("wm size").split("\n")
    ret.add(
        FunctionItem(
            icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_size_default, subTitle = wmSize[0].split(": ")[1],
            onLongClick = { /* IGNORE */ }, onClick = {
                adbExec("wm size reset")
                navToScreenDetail(requireNotNull(it))
            })
    )
    ret.add(
        FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_size_external, subTitle = (if (wmSize.size == 1) wmSize[0] else wmSize[1]).split(": ")[1],
            onClick = { navToScreenSizeEditor(requireNotNull(it)) }, onLongClick = { /* IGNORE */ })
    )
    val wmDensity = adbExec("wm density").split("\n")
    ret.add(
        FunctionItem(
            icon = Icons.Outlined.BrandingWatermark, titleId = R.string.detail_screen_density_default, subTitle = wmDensity[0].split(": ")[1],
            onLongClick = { /* IGNORE */ }, onClick = {
                adbExec("wm density reset")
                navToScreenDetail(requireNotNull(it))
            })
    )
    ret.add(
        FunctionItem(
            icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_density_external, subTitle = (if (wmDensity.size == 1) wmDensity[0] else wmDensity[1]).split(": ")[1],
            onClick = { navToScreenDensityEditor(requireNotNull(it)) }, onLongClick = { /* IGNORE */ })
    )
    return ret
}