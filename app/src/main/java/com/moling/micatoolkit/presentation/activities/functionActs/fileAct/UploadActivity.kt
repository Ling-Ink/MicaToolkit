package com.moling.micatoolkit.presentation.activities.functionActs.fileAct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Publish
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.activities.functionActs.IconButton
import com.moling.micatoolkit.presentation.navigator.navToFileSelector
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.pushFile
import com.moling.micatoolkit.presentation.utils.showToast
import java.io.File

@Composable
fun UploadFileAct(navController: NavController) {
    var isUploading by remember { mutableStateOf(false) }
    var uploadStatus by remember { mutableStateOf(0) }
    var fileUploadSource = ""
    var fileUploadName = ""
    if (global.containsKey("fileUploadSource")) {
        fileUploadSource = global.getString("fileUploadSource")
        fileUploadName = fileUploadSource.split("/").last()
    }
    MicaToolkitTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { UploadInfoHeader(header = stringResource(id = R.string.util_fileUpload)) }
            item {
                UploadInfoChip(icon = Icons.Outlined.Publish, label = global.getString("fileUploadTarget")) {
                    navController.popBackStack()
                }
            }
            item {
                UploadInfoChip(
                    icon = Icons.Outlined.Upload,
                    label = if (fileUploadSource.isEmpty()) stringResource(id = R.string.upload_selectSource) else fileUploadName
                ) {
                    navToFileSelector(navController)
                }
            }
            item {
                ConfirmButton {
                    Thread{
                        isUploading = true
                        val execResult = requireNotNull(Constants.adb).pushFile(
                            File(fileUploadSource),
                            "${global.getString("fileUploadTarget")}/${fileUploadName}"
                        )
                        uploadStatus = if (execResult.isEmpty()) {
                            "Upload Finished".showToast()
                            1
                        } else {
                            execResult.showToast()
                            -1
                        }
                    }.start()
                }
            }
        }
        if (isUploading) {
            when (uploadStatus) {
                0 -> UploadingProgress()
                -1 -> UploadedProgress(color = MaterialTheme.colors.error)
                1 -> UploadedProgress(color = Color(0xFF00C853))
            }
        }
    }
}

@Composable
fun UploadInfoHeader(
    header: String
) {
    ListHeader {
        Text(
            text = header,
            color = Color.White
        )
    }
}

@Composable
fun UploadInfoChip(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Chip(
        onClick = onClick,
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.fillMaxWidth(),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxHeight()) {
                Icon(imageVector = icon, contentDescription = "")
            }
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp)) {
                Text(
                    text = label,
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    )
}

@Composable
fun ConfirmButton(
    onClick: () -> Unit
) {
    IconButton(
        imageVector = Icons.Outlined.Done,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(top = 15.dp),
        onClick = onClick
    )
}

@Composable
fun UploadingProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
    )
}

@Composable
fun UploadedProgress(
    color: Color
) {
    CircularProgressIndicator(
        progress = 100f,
        color = color,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
    )
}