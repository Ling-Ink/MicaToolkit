package com.moling.micatoolkit.presentation.widgets.file

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CreateNewFolder
import androidx.compose.material.icons.outlined.UploadFile
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.utils.showToast
import com.moling.micatoolkit.presentation.widgets.func.FuncChip

@Composable
fun FileList(
    navController: NavController,
    location: String,
    fileList: List<FileItem>,
    isRemoteMode: Boolean = true,
    onFileSelect: (path: String) -> Unit,
    onDirChange: (path: String) -> Unit
) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ListHeader {
                Text(
                    text = location,
                    color = Color.White,
                    modifier = Modifier.clickable { /* TODO: File Manage Options */ }
                )
            }
        }
        item {
            Chip(
                onClick = { /* IGNORE */ },
                colors = ChipDefaults.secondaryChipColors(backgroundColor = Color.Transparent),
                label = {
                    BackChip {
                        onDirChange(location.split("/").dropLast(1).joinToString("/"))
                    }
                    if (isRemoteMode) {
                        CreateFolderChip()
                        UploadChip(navController)
                    }
                }
            )

        }
        items(fileList) {
            FuncChip(
                onClick = {
                    when (it.type) {
                        Constants.DIRECTORY -> {
                            onDirChange("${location}/${it.name}")
                        }
                        else -> {
                            onFileSelect("${location}/${it.name}")
                        }
                    }
                },
                onLongClick = {
                    "Long Click".showToast(Toast.LENGTH_SHORT)
                },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    FileChip(icon = it.icon, name = it.name)
                }
            )
        }
    }
}

@Composable
fun BackChip(
    onClick: () -> Unit,
) {
    Chip(
        onClick = onClick,
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.padding(end = 5.dp),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Composable
fun CreateFolderChip() {
    Chip(
        onClick = { /*TODO*/ },
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.CreateNewFolder, contentDescription = "")
            }
        }
    )
}

@Composable
fun UploadChip(
    navController: NavController,
) {
    Chip(
        onClick = {
            /* TODO */
        },
        colors = ChipDefaults.secondaryChipColors(),
        modifier = Modifier.padding(start = 5.dp),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.UploadFile, contentDescription = "")
            }
        }
    )
}

@Composable
fun FileChip(
    icon: ImageVector,
    name: String
) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxHeight()) {
        Icon(imageVector = icon, contentDescription = "")
    }
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxHeight()
        .padding(start = 10.dp)) {
        Text(
            text = name,
            color = Color.White,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}