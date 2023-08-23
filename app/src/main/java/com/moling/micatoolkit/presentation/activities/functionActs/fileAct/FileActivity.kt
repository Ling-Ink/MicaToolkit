package com.moling.micatoolkit.presentation.activities.functionActs.fileAct

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.activities.functionActs.swapList
import com.moling.micatoolkit.presentation.navigator.navToFileUploadFromSource
import com.moling.micatoolkit.presentation.utils.execShell
import com.moling.micatoolkit.presentation.utils.getFileType
import com.moling.micatoolkit.presentation.widgets.fileList.FileItem
import com.moling.micatoolkit.presentation.widgets.fileList.FileList
import java.io.File

@Composable
fun FilesAct(navController: NavController) {
    var location by remember { mutableStateOf("/storage/emulated/0") }
    val filesList = remember { mutableStateListOf<FileItem>() }
    FileList(
        navController = navController,
        location = location,
        fileList = filesList,
        isRemoteMode = global.getString("fileSource") == Constants.FILE_SOURCE_REMOTE,
        onFileSelect = {
            if (global.getString("fileSource") == Constants.FILE_SOURCE_LOCAL) {
                navToFileUploadFromSource(navController, it)
            }
        },
        onDirChange = {
            location = it
            Thread {
                when(global.getString("fileSource")) {
                    Constants.FILE_SOURCE_LOCAL -> filesList.swapList(loadLocalDirectory(location))
                    Constants.FILE_SOURCE_REMOTE -> filesList.swapList(loadRemoteDirectory(location))
                }
            }.start()
        }
    )
    LaunchedEffect(filesList) {
        Thread {
            when(global.getString("fileSource")) {
                Constants.FILE_SOURCE_LOCAL -> filesList.swapList(loadLocalDirectory(location))
                Constants.FILE_SOURCE_REMOTE -> filesList.swapList(loadRemoteDirectory(location))
            }
        }.start()
    }
}

fun loadLocalDirectory(path: String): List<FileItem> {
    val directoryList = mutableListOf<FileItem>()
    val filesList = mutableListOf<FileItem>()
    for (obj in File(path).listFiles()!!) {
        if (obj.isDirectory) {
            directoryList.add(
                FileItem(
                    icon = Icons.Outlined.Folder,
                    name = obj.name,
                    Constants.DIRECTORY
                )
            )
        } else if (obj.isFile) {
            filesList.add(
                FileItem(
                    icon = when(getFileType(obj.name)) {
                        Constants.IMAGE -> Icons.Outlined.Image
                        Constants.NO_PERM -> Icons.Outlined.Warning
                        Constants.APK -> Icons.Outlined.Android
                        else -> Icons.Filled.FileCopy
                    },
                    name = obj.name,
                )
            )
        }
    }
    return directoryList+filesList
}

fun loadRemoteDirectory(path: String): List<FileItem> {
    val directoryList = mutableListOf<FileItem>()
    val filesList = mutableListOf<FileItem>()
    for (obj in requireNotNull(Constants.adb).execShell("ls -AF $path").allOutput.split("\n")) {
        if (obj.endsWith("/")) {
            directoryList.add(
                FileItem(
                    icon = Icons.Outlined.Folder,
                    name = obj.dropLast(1),
                    Constants.DIRECTORY
                )
            )
        } else if (obj.endsWith("Permission denied")) {
            filesList.add(
                FileItem(
                    icon = Icons.Outlined.Warning,
                    name = obj.replace("ls: ", "").replace(": Permission denied", "")
                )
            )
        } else if (obj.isNotEmpty()) {
            filesList.add(
                FileItem(
                    icon = when(getFileType(obj)) {
                        Constants.IMAGE -> Icons.Outlined.Image
                        Constants.APK -> Icons.Outlined.Android
                        else -> Icons.Filled.FileCopy
                    },
                    name = obj
                )
            )
        }
    }
    return directoryList+filesList
}