package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AspectRatio
import androidx.compose.material.icons.outlined.Edit
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.utils.adbExec
import com.moling.micatoolkit.presentation.widgets.func.FunctionItem

fun screenInfoList(): List<FunctionItem> {
    val wmSize = adbExec("wm size").split("\n")
    val wmDensity = adbExec("wm density").split("\n")
    val ret = mutableListOf<FunctionItem>()
    if (wmSize.size == 1) {
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_size_default, subTitle = wmSize[0].split(": ")[1],
                onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_size_external, subTitle = wmSize[0].split(": ")[1],
                onClick = { /* TODO */ }, onLongClick = { /* IGNORE */ })
        )
    } else if (wmSize.size == 2) {
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_size_default, subTitle = wmSize[0].split(": ")[1],
                onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_size_external, subTitle = wmSize[1].split(": ")[1],
                onClick = { /* TODO */ }, onLongClick = { /* IGNORE */ })
        )
    }
    if (wmDensity.size == 1) {
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_density_default, subTitle = wmDensity[0].split(": ")[1],
                onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_density_external, subTitle = wmDensity[0].split(": ")[1],
                onClick = { /* TODO */ }, onLongClick = { /* IGNORE */ })
        )
    } else if (wmDensity.size == 2) {
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.AspectRatio, titleId = R.string.detail_screen_density_default, subTitle = wmDensity[0].split(": ")[1],
                onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ })
        )
        ret.add(
            FunctionItem(
                icon = Icons.Outlined.Edit, titleId = R.string.detail_screen_density_external, subTitle = wmDensity[1].split(": ")[1],
                onClick = { /* TODO */ }, onLongClick = { /* IGNORE */ })
        )
    }
    return ret
}