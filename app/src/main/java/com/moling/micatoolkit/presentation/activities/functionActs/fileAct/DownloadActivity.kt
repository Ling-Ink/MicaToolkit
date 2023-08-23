package com.moling.micatoolkit.presentation.activities.functionActs.fileAct

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.Download
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
import com.moling.micatoolkit.presentation.navigator.navToDirSelector
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.pullFile
import com.moling.micatoolkit.presentation.utils.showToast
import com.moling.micatoolkit.presentation.widgets.ConfirmButton
import java.io.File

@Composable
fun DownloadFileAct(navController: NavController) {
    var isDownloading by remember { mutableStateOf(false) }
    var downloadStatus by remember { mutableStateOf(0) }
    val fileDownloadSource = global.getString("fileDownloadSource")
    val fileDownloadName = fileDownloadSource.split("/").last()
    MicaToolkitTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { DownloadInfoHeader(header = stringResource(id = R.string.util_fileDownload)) }
            item {
                DownloadInfoChip(
                    icon = Icons.Outlined.ArrowDownward, onClick = { /* IGNORE */ },
                    label = if (fileDownloadSource.isEmpty()) stringResource(id = R.string.download_selectTarget) else fileDownloadName
                )
            }
            item {
                DownloadInfoChip(
                    icon = Icons.Outlined.Download,
                    label = if (global.containsKey("fileDownloadTarget")) global.getString("fileDownloadTarget") else stringResource(id = R.string.download_selectTarget)) {
                        navToDirSelector(navController)
                    }
            }
            item {
                ConfirmButton {
                    Thread{
                        isDownloading = true
                        val execResult = requireNotNull(Constants.adb).pullFile(
                            File("${global.getString("fileDownloadTarget")}/${fileDownloadName}"),
                            global.getString("fileDownloadSource")
                        )
                        downloadStatus = if (execResult.isEmpty()) {
                            "Download Finished".showToast()
                            1
                        } else {
                            execResult.showToast()
                            -1
                        }
                    }.start()
                }
            }
        }
        if (isDownloading) {
            when (downloadStatus) {
                0 -> DownloadingProgress()
                -1 -> DownloadedProgress(color = MaterialTheme.colors.error)
                1 -> DownloadedProgress(color = Color(0xFF00C853))
            }
        }
    }
}

@Composable
fun DownloadInfoHeader(
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
fun DownloadInfoChip(
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
fun DownloadingProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
    )
}

@Composable
fun DownloadedProgress(
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