package com.moling.micatoolkit.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector

class MenuItem(icon: ImageVector, label: String, route: String){
    val icon: ImageVector
    val label: String
    val route: String

    init {
        this.icon = icon
        this.label = label
        this.route = route
    }
}