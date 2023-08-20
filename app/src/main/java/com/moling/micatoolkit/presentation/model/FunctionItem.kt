package com.moling.micatoolkit.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector

class FunctionItem(
    icon: ImageVector,
    titleId: Int,
    subTitleId: Int = 0,
    onClickRoute: String? = null,
    onClickPopUpTo: String? = null,
    onLongClickRoute: String? = null,
    onLongClickPopUpTo: String? = null,
) {
    val icon: ImageVector
    val titleId: Int
    val subTitleId: Int
    val onClickRoute: String?
    val onClickPopUpTo: String?
    val onLongClickRoute: String?
    val onLongClickPopUpTo: String?

    init {
        this.icon = icon
        this.titleId = titleId
        this.subTitleId = subTitleId
        this.onClickRoute = onClickRoute
        this.onClickPopUpTo = onClickPopUpTo
        this.onLongClickRoute = onLongClickRoute
        this.onLongClickPopUpTo = onLongClickPopUpTo
    }
}