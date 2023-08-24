package com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Warning
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.navigator.navToFileDownload
import com.moling.micatoolkit.presentation.utils.execShell
import com.moling.micatoolkit.presentation.utils.getFileType
import com.moling.micatoolkit.presentation.utils.getSize
import com.moling.micatoolkit.presentation.widgets.functionList.FunctionItem

fun fileInfoList(): List<FunctionItem> {
    val filePath = global.getString("filePath")
    val fileType = global.getInt("fileType")
    val fileName = filePath.split("/").last()
    val ret = mutableListOf<FunctionItem>()
    ret.add(
        FunctionItem(
            icon = when (fileType) {
                Constants.DIRECTORY -> Icons.Outlined.Folder
                else -> {
                    when (getFileType(fileName)) {
                        Constants.IMAGE -> Icons.Outlined.Image
                        Constants.NO_PERM -> Icons.Outlined.Warning
                        Constants.APK -> Icons.Outlined.Android
                        else -> Icons.Filled.FileCopy
                    }
                }
            }, title = fileName, onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }
        )
    )
    when (fileType) {
        Constants.DIRECTORY -> {
            ret.add(
                FunctionItem(
                    icon = Icons.Outlined.Delete,
                    titleId = R.string.detail_file_delete, subTitleId = R.string.detail_file_delete_subTitle,
                    onClick = { /* IGNORE */ }, onLongClick = {
                        requireNotNull(Constants.adb).execShell("rm -r $filePath")
                        requireNotNull(it).popBackStack()
                    }
                )
            )
        }
        else -> {
            ret.add(
                FunctionItem(
                    icon = Icons.Outlined.Description,
                    titleId = R.string.detail_file_size, subTitle = requireNotNull(Constants.adb).getSize(filePath),
                    onClick = { /* IGNORE */ }, onLongClick = { /* IGNORE */ }
                )
            )
            ret.add(
                FunctionItem(
                    icon = Icons.Outlined.Delete,
                    titleId = R.string.detail_file_delete, subTitleId = R.string.detail_file_delete_subTitle,
                    onClick = { /* IGNORE */ }, onLongClick = {
                        requireNotNull(Constants.adb).execShell("rm -r $filePath")
                        requireNotNull(it).popBackStack()
                    }
                )
            )
            ret.add(
                FunctionItem(
                    icon = Icons.Outlined.Download,
                    titleId = R.string.detail_file_download,
                    onClick = { navToFileDownload(requireNotNull(it), filePath) }, onLongClick = { /* IGNORE */ }
                )
            )
        }
    }
    return ret
}