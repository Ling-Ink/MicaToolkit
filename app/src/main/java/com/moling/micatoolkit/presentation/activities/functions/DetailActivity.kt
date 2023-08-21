package com.moling.micatoolkit.presentation.activities.functions

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.moling.micatoolkit.presentation.DETAIL_APP_LIST
import com.moling.micatoolkit.presentation.DetailRoute
import com.moling.micatoolkit.presentation.FUNC_APPMGR
import com.moling.micatoolkit.presentation.FUNC_DEVICE
import com.moling.micatoolkit.presentation.FUNC_SCREEN
import com.moling.micatoolkit.presentation.ToolsRoute
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.fileUtils.RemoteFileUtils
import com.moling.micatoolkit.presentation.widgets.FunctionItem
import com.moling.micatoolkit.presentation.widgets.FunctionList

class DetailActivity : ComponentActivity()

var functionsList = mutableListOf<FunctionItem>()
var file = RemoteFileUtils()

@Composable
fun InitializeDetails(detailType: String) {
    functionsList.clear()
    when (detailType) {
        ToolsRoute.TOOL_DEVICE -> functionsList.addAll(FUNC_DEVICE())
        ToolsRoute.TOOL_SCREEN -> functionsList.addAll(FUNC_SCREEN())
        ToolsRoute.TOOL_APPS -> functionsList.addAll(FUNC_APPMGR())

        DetailRoute.DETAIL_APP_LIST -> functionsList.addAll(DETAIL_APP_LIST())
    }
}

@Composable
fun DetailAct(navController: NavHostController, detailType: String, titleId: Int) {
    file = RemoteFileUtils()
    MicaToolkitTheme {
        FunctionList(
            headerId = titleId,
            functionsList = functionsList,
            navController = navController
        )
        InitializeDetails(detailType = detailType)
    }
}