package com.moling.micatoolkit.presentation.activities.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Publish
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
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
import com.moling.micatoolkit.presentation.FileFunc
import com.moling.micatoolkit.presentation.FileSource
import com.moling.micatoolkit.presentation.ToolsRoute
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme

@Composable
fun UploadAct(navController: NavController, targetPath: String) {
    val targetPathNew: String = targetPath.replace("\\", "/")
    val selFileDefault = stringResource(id = R.string.txt_selectFile)
    val sourcePath by remember { mutableStateOf(selFileDefault) }
    MicaToolkitTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                ListHeader {
                    Text(
                        text = stringResource(id = R.string.file_upload),
                        color = Color.White
                    )
                }
            }
            item {
                IconChip(icon = Icons.Outlined.Publish, title = targetPathNew) {
                    navController.popBackStack()
                }
            }
            item {
                IconChip(icon = Icons.Outlined.Upload, title = sourcePath) {
                    navController.navigate("${ToolsRoute.TOOL_FILE}/${FileSource.SOURCE_LOCAL}/${Constants.NULL_PARAM_PLACEHOLDER}/${FileFunc.FUNC_TYPE_UPLOAD}")
                }
            }
            item {
                ButtonChip(icon = Icons.Outlined.Done) {

                }
            }
        }
    }
}

@Composable
fun IconChip(
    icon: ImageVector,
    title: String,
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
                .fillMaxHeight()) {
                Text(
                    text = title,
                    color = Color.White,
                )
            }
        }
    )
}

@Composable
fun ButtonChip(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Chip(
        onClick = onClick,
        colors = ChipDefaults.secondaryChipColors(backgroundColor = MaterialTheme.colors.primary),
        label = {
            Column(verticalArrangement = Arrangement.Center) {
                Icon(imageVector = icon, contentDescription = "")
            }
        }
    )
}