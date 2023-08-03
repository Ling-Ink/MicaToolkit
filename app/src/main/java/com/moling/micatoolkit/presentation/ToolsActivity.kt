package com.moling.micatoolkit.presentation

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.MenuItem
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.showToast
import dadb.AdbKeyPair
import dadb.Dadb
import java.io.File
import java.io.FileNotFoundException
import java.net.ConnectException

class ToolActivity : ComponentActivity()

var menuListCon = mutableListOf<MenuItem>()

@Composable
fun InitializeMenuList() {
    menuListCon.clear()
    menuListCon.add(
        MenuItem(
            icon = Icons.Outlined.PermDeviceInformation,
            label = stringResource(id = R.string.menu_deviceInfo),
            route = ToolsRoute.TOOL_DEVICE
        )
    )
    menuListCon.add(
        MenuItem(
            icon = Icons.Outlined.FitScreen,
            label = stringResource(id = R.string.menu_screenInfo),
            route = ToolsRoute.TOOL_SCREEN
        )
    )
    menuListCon.add(
        MenuItem(
            icon = Icons.Outlined.Folder,
            label = stringResource(id = R.string.menu_fileList),
            route = ToolsRoute.TOOL_FILE
        )
    )
}

@Composable
fun ToolAct(navController: NavHostController, host: String, port: Int) {
    InitializeMenuList()
    MicaToolkitTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            ToolList(navController, host, port)
        }
    }
}

@Composable
fun ToolList(navController: NavHostController, host: String, port: Int) {
    var connectStatus by remember { mutableStateOf(0) }
    val menuList = remember { mutableStateListOf<MenuItem>() }
    connectStatus = adbConnect(host, port)
    if (connectStatus == 1)
        menuList.swapList(menuListCon)
    ScalingLazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ListHeader {
                Text(
                    text = stringResource(id = R.string.txt_toolsList),
                    color = Color.White
                )
            }
        }
        item {
            Chip(
                onClick = {  },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillParentMaxHeight()) {
                        when (connectStatus) {
                            0 -> {
                                CircularProgressIndicator()
                            }
                            1 -> {
                                Icon(imageVector = Icons.Outlined.Done, contentDescription = "")
                            }
                            -1 -> {
                                Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                            }
                        }
                    }
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillParentMaxHeight()) {
                        when (connectStatus) {
                            0 -> {
                                Text(
                                    text = stringResource(id = R.string.status_connecting),
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                            1 -> {
                                Text(
                                    text = stringResource(id = R.string.status_connected),
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                            -1 -> {
                                Text(
                                    text = stringResource(id = R.string.status_connectfailed),
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                        }
                        Text(
                            text = "${host}:${port}",
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            )
        }
        if (connectStatus == 1) {
            items(menuList) {
                Chip(
                    onClick = {
                              navController.navigate("${AppNavRoute.ROUTE_DETAIL}/${it.route}") {
                                  popUpTo(AppNavRoute.ROUTE_TOOL)
                              }
                    },
                    colors = ChipDefaults.secondaryChipColors(),
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Icon(imageVector = it.icon, contentDescription = "")
                        Text(
                            text = it.label,
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                )
            }
        }
    }
}

private val ADB_PRIVATE_KEY_PATH = "${requireNotNull(Constants.filesDir).absolutePath}/adbkey"
private val ADB_PUBLIC_KEY_PATH = "${requireNotNull(Constants.filesDir).absolutePath}/adbkey.pub"
fun adbConnect(host: String, port: Int): Int {
    if (Constants.adb != null)
        requireNotNull(Constants.adb).close()
    val keyPair: AdbKeyPair
    try {
        keyPair = AdbKeyPair.read(
            File(ADB_PRIVATE_KEY_PATH),
            File(ADB_PUBLIC_KEY_PATH)
        )
    } catch (e: FileNotFoundException) {
        AdbKeyPair.generate(
            File(ADB_PRIVATE_KEY_PATH),
            File(ADB_PUBLIC_KEY_PATH)
        )
        return adbConnect(host, port)
    }
    Log.d("MICA", "ADB Connecting to ${host}:${port}")
    Constants.adb = Dadb.create(host, port, keyPair)
    var result = 1
    val thread = Thread {
        try {
            Log.d("MICA", requireNotNull(Constants.adb).shell("whoami").toString())
        } catch (e: ConnectException) {
            e.toString().showToast(Toast.LENGTH_SHORT)
            result = -1
        }
    }
    thread.start()
    thread.join()

    return result
}