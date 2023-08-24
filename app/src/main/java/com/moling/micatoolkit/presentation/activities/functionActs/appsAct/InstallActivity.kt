package com.moling.micatoolkit.presentation.activities.functionActs.appsAct

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadInfoChip
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadInfoHeader
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadedProgress
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadingProgress
import com.moling.micatoolkit.presentation.navigator.navToFileSelector
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.installApk
import com.moling.micatoolkit.presentation.utils.showToast
import com.moling.micatoolkit.presentation.widgets.ConfirmButton
import java.io.File

@Composable
fun InstallAct(navController: NavController) {
    var isUninstalling by remember { mutableStateOf(false) }
    var installStatus by remember { mutableStateOf(0) }
    var installSource = ""
    var installName = ""
    if (global.containsKey("fileUploadSource")) {
        installSource = global.getString("fileUploadSource")
        installName = installSource.split("/").last()
    }
    MicaToolkitTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { UploadInfoHeader(header = stringResource(id = R.string.detail_appInstall)) }
            item {
                UploadInfoChip(
                    icon = Icons.Outlined.Android,
                    label = if (installSource.isEmpty()) stringResource(id = R.string.install_selectFile) else installName
                ) {
                    navToFileSelector(navController)
                }
            }
            item {
                ConfirmButton {
                    if (installName.split(".").last() != "apk") {
                        isUninstalling = true
                        installStatus = -1
                    } else {
                        Thread {
                            isUninstalling = true
                            val execResult = requireNotNull(Constants.adb).installApk(
                                File(installSource)
                            )
                            installStatus = if (execResult.isEmpty()) {
                                1
                            } else {
                                execResult.showToast()
                                -1
                            }
                        }.start()
                    }
                }
            }
        }
        if (isUninstalling) {
            when (installStatus) {
                0 -> UploadingProgress()
                -1 -> UploadedProgress(color = MaterialTheme.colors.error)
                1 -> UploadedProgress(color = Color(0xFF00C853))
            }
        }
    }
}