package com.moling.micatoolkit.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector

class FileDisplay(icon: ImageVector, title: String, content: String, route: String?) {
    val icon: ImageVector
    val title: String
    val content: String
    val route: String?

    init {
        this.icon = icon
        this.title = title
        this.content = content
        this.route = route
    }
}