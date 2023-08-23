package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import com.moling.micatoolkit.presentation.utils.adbExec
import com.moling.micatoolkit.presentation.widgets.func.FunctionItem

fun appList(): List<FunctionItem> {
    val packages = adbExec("pm list packages -3").split("\n")
    return packages.map {
        FunctionItem(
            icon = Icons.Outlined.Android,
            title = it.replace("package:", ""),
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        )
    }
}