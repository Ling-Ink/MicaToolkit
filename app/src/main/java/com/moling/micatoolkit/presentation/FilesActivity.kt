package com.moling.micatoolkit.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.DetailItem
import com.moling.micatoolkit.presentation.model.FileItem
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.fileUtils.LocalFileUtils
import com.moling.micatoolkit.presentation.utils.fileUtils.RemoteFileUtils

class FilesActivity : ComponentActivity()

var remoteFileUtil: RemoteFileUtils? = null
var localFileUtil: LocalFileUtils? = null

fun loadFiles(fileSource: String): List<DetailItem> {
    val fileItem = mutableListOf<DetailItem>()
    fileItem.add(DetailItem(Icons.Outlined.ArrowBack, "..", "", ".."))

    val dirs = mutableListOf<FileItem>()
    if (fileSource == FileSource.SOURCE_REMOTE)
        dirs.addAll(requireNotNull(remoteFileUtil).getSubs())
    if (fileSource == FileSource.SOURCE_LOCAL)
        dirs.addAll(requireNotNull(localFileUtil).getSubs())

    fileItem.addAll(
        dirs.map { fileItem ->
            DetailItem(
                when (fileItem.fileType) {
                    Constants.TYPE_DIRECTORY -> Icons.Outlined.Folder
                    Constants.TYPE_IMAGE -> Icons.Outlined.Image
                    Constants.TYPE_NO_PERM -> Icons.Outlined.Warning
                    else -> Icons.Filled.FileCopy
                },
                fileItem.fileName, "",
                if (fileItem.fileType == Constants.TYPE_DIRECTORY) fileItem.fileName else null
            )
        }
    )
    return fileItem
}

fun getLoc(fileSource: String): String {
    return when (fileSource) {
        FileSource.SOURCE_LOCAL -> {
            requireNotNull(localFileUtil).getLoc()
        }
        FileSource.SOURCE_REMOTE -> {
            requireNotNull(remoteFileUtil).getLoc()
        }
        else -> {
            ""
        }
    }
}

fun changeDir(fileSource: String, folder: String) {
    when (fileSource) {
        FileSource.SOURCE_LOCAL -> {
            requireNotNull(localFileUtil).cd(folder)
        }
        FileSource.SOURCE_REMOTE -> {
            requireNotNull(remoteFileUtil).cd(folder)
        }
    }
}

fun changeParent(fileSource: String) {
    when (fileSource) {
        FileSource.SOURCE_LOCAL -> {
            requireNotNull(localFileUtil).parent()
        }
        FileSource.SOURCE_REMOTE -> {
            requireNotNull(remoteFileUtil).parent()
        }
    }
}

@Composable
fun FilesAct(fileSource: String) {
    val fileListRem = remember { mutableStateListOf<DetailItem>() }
    val folderLocation = remember { mutableStateOf("") }

    if (fileSource == FileSource.SOURCE_REMOTE)
        remoteFileUtil = RemoteFileUtils()
    if (fileSource == FileSource.SOURCE_LOCAL)
        localFileUtil = LocalFileUtils()

    fileListRem.swapList(loadFiles(fileSource))
    folderLocation.value = getLoc(fileSource)

    MicaToolkitTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                ListHeader {
                    Text(
                        text = folderLocation.value,
                        color = Color.White
                    )
                }
            }
            items(fileListRem) {
                Chip(
                    onClick = {
                        when (it.route) {
                            ".." -> {
                                changeParent(fileSource)

                                fileListRem.swapList(loadFiles(fileSource))
                                folderLocation.value = getLoc(fileSource)
                            }
                            null -> {/* Do Nothing */}
                            else -> {
                                changeDir(fileSource, it.route)

                                fileListRem.swapList(loadFiles(fileSource))
                                folderLocation.value = getLoc(fileSource)
                            }
                        }
                    },
                    colors = ChipDefaults.secondaryChipColors(),
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                            .fillParentMaxHeight()) {
                            Icon(imageVector = it.icon, contentDescription = "")
                        }
                        Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                            .fillParentMaxHeight()
                            .padding(start = 10.dp)) {
                            Text(
                                text = it.title,
                                color = Color.White,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}