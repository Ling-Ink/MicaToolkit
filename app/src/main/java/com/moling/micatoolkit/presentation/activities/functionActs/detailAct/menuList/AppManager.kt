package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.InstallMobile
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.navigator.navToAppListDetail
import com.moling.micatoolkit.presentation.widgets.functionList.FunctionItem

fun appManagerList(): List<FunctionItem> {
    return listOf(
        FunctionItem(
            icon = Icons.Outlined.GridView,
            titleId = R.string.detail_appList,
            onClick = { navToAppListDetail(requireNotNull(it)) },
            onLongClick = { /* IGNORE */ }
        ),
        FunctionItem(
            icon = Icons.Outlined.InstallMobile,
            titleId = R.string.detail_appInstall,
            onClick = { /* TODO */ },
            onLongClick = { /* IGNORE */ }
        ),
    )
}