package com.moling.micatoolkit.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector

class MenuItem(icon: ImageVector, labelId: Int, route: String){
    val icon: ImageVector
    val labelId: Int
    val route: String

    init {
        this.icon = icon
        this.labelId = labelId
        this.route = route
    }
}