package com.moling.micatoolkit.presentation.widgets.file

import androidx.compose.ui.graphics.vector.ImageVector
import com.moling.micatoolkit.presentation.utils.getFileType

class FileItem(icon: ImageVector, name: String, type: Int? = null) {
    val icon: ImageVector
    val name: String
    val type: Int

    init {
        this.icon = icon
        this.name = name
        this.type = type ?: getFileType(name)
    }
}