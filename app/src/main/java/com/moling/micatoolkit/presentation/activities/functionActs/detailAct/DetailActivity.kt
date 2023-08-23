package com.moling.micatoolkit.presentation.activities.functionActs.detailAct

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList.appList
import com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList.appManagerList
import com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList.deviceInfoList
import com.moling.micatoolkit.presentation.activities.functionActs.detailAct.menuList.screenInfoList
import com.moling.micatoolkit.presentation.activities.functionActs.swapList
import com.moling.micatoolkit.presentation.navigator.NavRoute
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.widgets.func.FunctionItem
import com.moling.micatoolkit.presentation.widgets.func.FunctionList

class DetailActivity : ComponentActivity()

@Composable
fun DetailAct(navController: NavHostController) {
    val funcList = remember { mutableStateListOf<FunctionItem>() }
    MicaToolkitTheme {
        FunctionList(
            headerId = global.getInt("detailTitleId"),
            functionsList = funcList,
            navController = navController
        )
    }
    Thread {
        when (global.getString("detailType")) {
            NavRoute.TOOL_DEVICE -> funcList.swapList(deviceInfoList())
            NavRoute.TOOL_SCREEN -> funcList.swapList(screenInfoList())
            NavRoute.TOOL_APPMGR -> funcList.swapList(appManagerList())

            NavRoute.DETAIL_APP_LIST -> funcList.swapList(appList())
        }
    }.start()
}