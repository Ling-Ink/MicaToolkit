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
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadInfoChip
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadInfoHeader
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadedProgress
import com.moling.micatoolkit.presentation.activities.functionActs.fileAct.UploadingProgress
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.showToast
import com.moling.micatoolkit.presentation.utils.uninstallApk
import com.moling.micatoolkit.presentation.widgets.ConfirmButton

@Composable
fun UninstallAct() {
    var isUninstalling by remember { mutableStateOf(false) }
    var uninstallStatus by remember { mutableStateOf(0) }
    val packageName = global.getString("packageName")
    MicaToolkitTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { UploadInfoHeader(header = stringResource(id = R.string.util_appUninstall)) }
            item {
                UploadInfoChip(icon = Icons.Outlined.Android, label = packageName) {
                    /* IGNORE */
                }
            }
            item {
                ConfirmButton {
                    Thread{
                        isUninstalling = true
                        val execResult = requireNotNull(Constants.adb).uninstallApk(packageName)
                        uninstallStatus = if (execResult.isEmpty()) {
                            1
                        } else {
                            execResult.showToast()
                            -1
                        }
                    }.start()
                }
            }
        }
        if (isUninstalling) {
            when (uninstallStatus) {
                0 -> UploadingProgress()
                -1 -> UploadedProgress(color = MaterialTheme.colors.error)
                1 -> UploadedProgress(color = Color(0xFF00C853))
            }
        }
    }
}