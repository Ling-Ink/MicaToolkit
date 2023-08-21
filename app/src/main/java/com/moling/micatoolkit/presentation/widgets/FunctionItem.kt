package com.moling.micatoolkit.presentation.widgets

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

class FunctionItem(
    icon: ImageVector,
    background: Color? = null,
    title: String? = null,
    titleId: Int? = null,
    subTitle: String? = null,
    subTitleId: Int? = null,
    onClick: (navController: NavHostController?) -> Unit,
    onLongClick: (navController: NavHostController?) -> Unit,
) {
    val icon: ImageVector
    val color: Color?
    val title: String?
    val titleId: Int?
    val subTitle: String?
    val subTitleId: Int?
    val onClick: (navController: NavHostController?) -> Unit
    val onLongClick: (navController: NavHostController?) -> Unit

    init {
        this.icon = icon
        this.color = background
        this.title = title
        this.titleId = titleId
        this.subTitle = subTitle
        this.subTitleId = subTitleId
        this.onClick = onClick
        this.onLongClick = onLongClick
    }
}