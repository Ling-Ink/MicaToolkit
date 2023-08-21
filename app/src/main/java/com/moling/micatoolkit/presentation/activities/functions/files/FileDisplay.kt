package com.moling.micatoolkit.presentation.activities.functions.files

import androidx.compose.ui.graphics.vector.ImageVector

class FileDisplay(icon: ImageVector, title: String, route: String?) {
    val icon: ImageVector
    val title: String
    val route: String?

    init {
        this.icon = icon
        this.title = title
        this.route = route
    }
}