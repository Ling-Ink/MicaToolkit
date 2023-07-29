package com.moling.micatoolkit.presentation

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import androidx.wear.compose.material.MaterialTheme.colors
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.checkHost
import com.moling.micatoolkit.presentation.utils.getHost
import com.moling.micatoolkit.presentation.utils.getIpAddress
import com.moling.micatoolkit.presentation.utils.showToast
import java.net.InetAddress

class TargetActivity : ComponentActivity()

fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}

@Composable
fun TargetAct(navController: NavHostController) {
    val addressList = remember { mutableStateListOf<String>() }
    var progress by remember {  mutableStateOf(0.0f) }
    MicaToolkitTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            DeviceList(
                deviceList = addressList,
                onRefreshClick = {
                    Thread {
                        val hosts: List<String>
                        try {
                            hosts = InetAddress.getByName(getIpAddress()).getHost()

                            val hostsReachable = mutableListOf<String>()
                            var count = 0
                            for (host in hosts) {
                                count ++
                                progress = count / 255f
                                if (checkHost(host)) {
                                    hostsReachable.add(host)
                                }
                            }
                            addressList.swapList(hostsReachable)
                            progress = 0f
                        } catch (e: NumberFormatException) {
                            e.toString().showToast(Toast.LENGTH_SHORT)
                        }
                    }.start()
                },
                onHostClick = {
                    navController.navigate("${AppNavRoute.ROUTE_PORT}/${it}") {
                        popUpTo(AppNavRoute.ROUTE_MAIN)
                    }
                }
            )
        }
        androidx.compose.material3.CircularProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        )
    }
}

@Composable
fun DeviceList(deviceList: List<String>, onRefreshClick: () -> Unit, onHostClick: (item: String) -> Unit) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ListHeader {
                Text(
                    text = stringResource(id = R.string.txt_deviceList),
                    color = Color.White
                )
            }
        }
        item {
            Chip(
                onClick = onRefreshClick,
                colors = ChipDefaults.secondaryChipColors(),
                label = {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )
        }
        items(deviceList) { item: String ->
            Chip(
                onClick = { onHostClick(item) },
                colors = ChipDefaults.secondaryChipColors(),
                label = {
                    Text(
                        text = item,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
