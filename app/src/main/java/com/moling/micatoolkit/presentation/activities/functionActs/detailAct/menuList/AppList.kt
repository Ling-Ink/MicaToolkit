package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import com.moling.micatoolkit.presentation.navigator.navToAppPackageInfo
import com.moling.micatoolkit.presentation.utils.adbExec
import com.moling.micatoolkit.presentation.widgets.functionList.FunctionItem

fun appList(): List<FunctionItem> {
    val packages = adbExec("pm list packages").split("\n")
    return packages.map { packageName ->
        FunctionItem(
            icon = Icons.Outlined.Android,
            title = packageName.replace("package:", ""),
            onClick = { navToAppPackageInfo(requireNotNull(it), packageName.replace("package:", "")) },
            onLongClick = { /* IGNORE */ }
        )
    }
}