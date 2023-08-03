package com.moling.micatoolkit.presentation.utils

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.FileItem


class FileUtils(location: String = "/storage/emulated/0") {
    private var location: String

    init {
        this.location = location
    }

    fun getSubs(): List<FileItem> {
        val execResult = requireNotNull(Constants.adb).execShell("ls -AF $location")
        Log.d("MICA", "err: ${execResult.errorOutput}")
        val ls = execResult.allOutput.split("\n")
        Log.d("MICA", "dirs: ")
        for (dir in ls) {
            Log.d("MICA", "     $dir")
        }
        val subsList = mutableStateListOf<FileItem>()
        for (obj in ls) {
            if (obj.endsWith("/")) {
                subsList.add(FileItem(obj.dropLast(1), Constants.TYPE_DIRECTORY))
            } else if (obj.endsWith("Permission denied")) {
                subsList.add(FileItem(obj.replace("ls: ", "").replace(": Permission denied", ""), Constants.TYPE_NO_PERM))
            } else if (obj.isNotEmpty()) {
                subsList.add(FileItem(obj, getFileType(obj)))
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