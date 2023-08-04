package com.moling.micatoolkit.presentation.utils.fileUtils

import androidx.compose.runtime.mutableStateListOf
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.FileItem
import com.moling.micatoolkit.presentation.utils.getFileType
import java.io.File

class LocalFileUtils(location: String = "/storage/emulated/0") {
    private var location: String

    init {
        this.location = location
    }

    fun getSubs(): List<FileItem> {
        val subsList = mutableStateListOf<FileItem>()
        val external = File(location).listFiles() ?: return subsList
        for (obj in external) {
            if (obj.isDirectory) {
                subsList.add(FileItem(obj.name, Constants.TYPE_DIRECTORY))
            } else if (obj.isFile) {
                subsList.add(FileItem(obj.name, getFileType(obj.name)))
            }
        }
        return subsList
    }

    fun parent() {
        val dirs = location.split("/")
        location = dirs.dropLast(1).joinToString(separator = "/")
    }

    fun cd(folder: String) {
        location += "/$folder"
    }

    fun getLoc(): String {
        return location
    }
}