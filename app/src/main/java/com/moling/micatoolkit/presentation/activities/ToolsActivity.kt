package com.moling.micatoolkit.presentation.activities

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.FUNC_TOOLS
import com.moling.micatoolkit.presentation.model.Constants
import com.moling.micatoolkit.presentation.model.FunctionItem
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.showToast
import com.moling.micatoolkit.presentation.widgets.FunctionList
import dadb.AdbKeyPair
import dadb.Dadb
import java.io.File
import java.io.FileNotFoundException
import java.net.ConnectException

class ToolActivity : ComponentActivity()

var functionList = mutableListOf<FunctionItem>()

@Composable
fun InitializeMenuList() {
    functionList.clear()
    functionList.addAll(FUNC_TOOLS())
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
    val funcList = remember { mutableStateListOf<FunctionItem>() }
    FunctionList(
        headerId = R.string.txt_toolsList,
        navController = navController,
        functionsList = funcList,
        headerChip = {
            Chip(
                onClick = {  },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillParentMaxHeight()) {
                        when (connectStatus) {
                            0 -> CircularProgressIndicator()
                            1 -> Icon(imageVector = Icons.Outlined.Done, contentDescription = "")
                            -1 -> Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                        }
                    }
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillParentMaxHeight()) {
                        when (connectStatus) {
                            0 -> HeaderText(id = R.string.status_connecting)
                            1 -> HeaderText(id = R.string.status_connected)
                            -1 -> HeaderText(id = R.string.status_connectfailed)
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
    )
    Thread {
        if (connectStatus == 0) {
            connectStatus = adbConnect(host, port)
            if (connectStatus == 1)
                funcList.swapList(functionList)
        }
    }.start()
}

@Composable
fun HeaderText(id: Int) {
    Text(
        text = stringResource(id = id),
        color = Color.White,
        modifier = Modifier.padding(start = 5.dp)
    )
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