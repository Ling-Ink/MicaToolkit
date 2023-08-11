package com.moling.micatoolkit.presentation.activities

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.DETAIL_DEVICE
import com.moling.micatoolkit.presentation.DETAIL_SCREEN
import com.moling.micatoolkit.presentation.ToolsRoute
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.DetailItem
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.*
import com.moling.micatoolkit.presentation.utils.fileUtils.RemoteFileUtils

class DetailActivity : ComponentActivity()

var detailList = mutableListOf<DetailItem>()
var file = RemoteFileUtils()

@Composable
fun InitializeDetails(detailType: String) {
    detailList.clear()
    when (detailType) {
        ToolsRoute.TOOL_DEVICE -> detailList.addAll(DETAIL_DEVICE())
        ToolsRoute.TOOL_SCREEN -> detailList.addAll(DETAIL_SCREEN())
    }
}

@Composable
fun DetailAct(detailType: String) {
    file = RemoteFileUtils()
    MicaToolkitTheme {
        val detailListRem = remember { mutableStateListOf<DetailItem>() }
        InitializeDetails(detailType = detailType)
        detailListRem.swapList(detailList)

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
                    }
                }
            }
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
                                text = stringResource(id = it.titleId),
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