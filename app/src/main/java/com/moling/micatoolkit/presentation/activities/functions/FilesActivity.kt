package com.moling.micatoolkit.presentation.activities.functions

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.moling.micatoolkit.presentation.AppNavRoute
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.FileFunc
import com.moling.micatoolkit.presentation.FileSource
import com.moling.micatoolkit.presentation.ToolsRoute
import com.moling.micatoolkit.presentation.utils.execShell
import com.moling.micatoolkit.presentation.utils.getFileType
import com.moling.micatoolkit.presentation.utils.pushFile
import com.moling.micatoolkit.presentation.widgets.file.FileItem
import com.moling.micatoolkit.presentation.widgets.file.FileList
import java.io.File

@Composable
fun FilesAct(navController: NavController, fileSource: String, funcType: String, dirPath: String = "\\storage\\emulated\\0") {
    val dirPathNew = dirPath.replace("\\", "/")
    val filesList = mutableListOf<FileItem>()
    when(fileSource) {
        FileSource.SOURCE_LOCAL -> {
            for (obj in File(dirPathNew).listFiles()!!) {
                if (obj.isDirectory) {
                    filesList.add(
                        FileItem(
                            icon = Icons.Outlined.Folder,
                            name = obj.name,
                            Constants.TYPE_DIRECTORY
                        )
                    )
                } else if (obj.isFile) {
                    filesList.add(
                        FileItem(
                            icon = when(getFileType(obj.name)) {
                                Constants.TYPE_IMAGE -> Icons.Outlined.Image
                                Constants.TYPE_NO_PERM -> Icons.Outlined.Warning
                                Constants.TYPE_APK -> Icons.Outlined.Android
                                else -> Icons.Filled.FileCopy
                            },
                            name = obj.name,
                        )
                    )
                }
            }
        }
        FileSource.SOURCE_REMOTE -> {
            for (obj in requireNotNull(Constants.adb).execShell("ls -AF $dirPathNew").allOutput.split("\n")) {
                if (obj.endsWith("/")) {
                    filesList.add(
                        FileItem(
                            icon = Icons.Outlined.Folder,
                            name = obj.dropLast(1),
                            Constants.TYPE_DIRECTORY
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
                                Constants.TYPE_IMAGE -> Icons.Outlined.Image
                                Constants.TYPE_APK -> Icons.Outlined.Android
                                else -> Icons.Filled.FileCopy
                            },
                            name = obj
                        )
                    )
                }
            }
        }
    }
    FileList(
        navController = navController,
        location = dirPathNew,
        fileList = filesList,
        isRemoteMode = fileSource == FileSource.SOURCE_REMOTE,
        onFileSelect = {
            Log.d("MICA", "funcType: $funcType")
            when (funcType) {
                FileFunc.FUNC_TYPE_UPLOAD -> {
                    val result = requireNotNull(Constants.adb).pushFile(File(it), "${Constants.uploadDstPath}/")
                    Log.d("MICA", "result: $result")
                }
            }
        },
        onDirChange = {
            Log.d("MICA", it)
            navController.navigate("${ToolsRoute.TOOL_FILE}/${fileSource}/${it.replace("/", "\\")}/${funcType}")
        }
    )
}