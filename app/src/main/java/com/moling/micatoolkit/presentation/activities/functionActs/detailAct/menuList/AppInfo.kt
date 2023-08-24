package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.Delete
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.navigator.navToAppUninstall
import com.moling.micatoolkit.presentation.widgets.functionList.FunctionItem

fun appInfoList(): List<FunctionItem> {
    val packageName = global.getString("packageName")
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.Android,
            title = packageName,
            onClick = { /* IGNORE */ },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.Delete,
            titleId = R.string.util_appUninstall,
            onClick = { navToAppUninstall(requireNotNull(it)) },
            onLongClick = { /* IGNORE */ }
        ),
    )
}