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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.DetailItem
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.*

class DetailActivity : ComponentActivity()

var detailList = mutableListOf<DetailItem>()
var file = FileUtils()

@Composable
fun InitializeDetails(detailType: String) {
    detailList.clear()
    when (detailType) {
        ToolsRoute.TOOL_DEVICE -> {
            detailList.add(requireNotNull(Constants.adb).getDeviceBrand(stringResource(id = R.string.detail_device_brand)))
            detailList.add(requireNotNull(Constants.adb).getDeviceModel(stringResource(id = R.string.detail_device_model)))
            detailList.add(requireNotNull(Constants.adb).getDeviceAndroidId(stringResource(id = R.string.detail_device_androidID)))
            detailList.add(requireNotNull(Constants.adb).getDeviceAndroidBuild(stringResource(id = R.string.detail_device_build)))
            detailList.add(requireNotNull(Constants.adb).getDeviceAndroidSDKVersion(stringResource(id = R.string.detail_device_sdk)))
            detailList.add(requireNotNull(Constants.adb).getDeviceAndroidSecPatch(stringResource(id = R.string.detail_device_patch)))
        }
        ToolsRoute.TOOL_SCREEN -> {
            detailList.addAll(requireNotNull(Constants.adb).getScreenSize(stringResource(R.string.detail_screen_size_default), stringResource(R.string.detail_screen_size_external)))
            detailList.addAll(requireNotNull(Constants.adb).getScreenDensity(stringResource(R.string.detail_screen_density_default), stringResource(R.string.detail_screen_density_external)))
        }
        ToolsRoute.TOOL_FILE -> {
            loadFile()
        }
    }
}

fun loadFile() {
    detailList.add(DetailItem(Icons.Outlined.ArrowBack, "..", "", ".."))
    val dirs = file.getSubs()
    detailList.addAll(
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
}

@Composable
fun DetailAct(detailType: String) {
    file = FileUtils()
    MicaToolkitTheme {
        val detailListRem = remember { mutableStateListOf<DetailItem>() }
        InitializeDetails(detailType = detailType)
        detailListRem.swapList(detailList)

        val detailHeader = remember { mutableStateOf("") }
        if (detailType == ToolsRoute.TOOL_FILE)
            detailHeader.value = file.getLoc()

        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                ListHeader {
                    when (detailType) {
                        ToolsRoute.TOOL_DEVICE -> {
                            Text(
                                text = stringResource(id = R.string.menu_deviceInfo),
                                color = Color.White
                            )
                        }
                        ToolsRoute.TOOL_SCREEN -> {
                            Text(
                                text = stringResource(id = R.string.menu_screenInfo),
                                color = Color.White
                            )
                        }
                        ToolsRoute.TOOL_FILE -> {
                            Text(
                                text = detailHeader.value,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            if (detailType == ToolsRoute.TOOL_FILE) {
                items(detailListRem) {
                    Chip(
                        onClick = {
                                  when (it.route) {
                                      ".." -> {
                                          file.parent()
                                          detailList.clear()
                                          loadFile()
                                          detailListRem.swapList(detailList)
                                          detailHeader.value = file.getLoc()
                                      }
                                      null -> {/* Do Nothing */}
                                      else -> {
                                          file.cd(it.route)
                                          detailList.clear()
                                          loadFile()
                                          detailListRem.swapList(detailList)
                                          detailHeader.value = file.getLoc()
                                      }
                                  }
                        },
                        colors = ChipDefaults.secondaryChipColors(),
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Icon(imageVector = it.icon, contentDescription = "")
                            Text(
                                text = it.title,
                                color = Color.White,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    )
                }
            } else {
                items(detailListRem) {
                    Chip(
                        onClick = { /*TODO*/ },
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
                                Text(
                                    text = it.content,
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}