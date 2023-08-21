package com.moling.micatoolkit.presentation.activities.functions

import androidx.compose.ui.graphics.vector.ImageVector

class FunctionItem(
    icon: ImageVector,
    title: String? = null,
    titleId: Int? = null,
    subTitle: String? = null,
    subTitleId: Int? = null,
    onClickRoute: String? = null,
    onClickPopUpTo: String? = null,
    onLongClickRoute: String? = null,
    onLongClickPopUpTo: String? = null,
) {
    val icon: ImageVector
    val title: String?
    val titleId: Int?
    val subTitle: String?
    val subTitleId: Int?
    val onClickRoute: String?
    val onClickPopUpTo: String?
    val onLongClickRoute: String?
    val onLongClickPopUpTo: String?

    init {
        this.icon = icon
        this.title = title
        this.titleId = titleId
        this.subTitle = subTitle
        this.subTitleId = subTitleId
        this.onClickRoute = onClickRoute
        this.onClickPopUpTo = onClickPopUpTo
        this.onLongClickRoute = onLongClickRoute
        this.onLongClickPopUpTo = onLongClickPopUpTo
    }
}