package com.moling.micatoolkit.presentation.activities.options

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.AppNavRoute
import com.moling.micatoolkit.presentation.activities.IconButton
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme

class PortActivity : ComponentActivity()

@Composable
fun PortAct(navController: NavHostController, host: String) {
    var port by remember {  mutableStateOf("") }
    var fieldError by remember { mutableStateOf(false) }
    MicaToolkitTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
                ) {
                    TextField(
                        value = port,
                        onValueChange = { port = it },
                        placeholder = { Text(text = stringResource(id = R.string.txt_remotePort), color = MaterialTheme.colors.surface) },
                        textStyle = TextStyle(
                            color = Color.White
                        ),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        ),
                        isError = fieldError
                    )
                    IconButton(
                        imageVector = Icons.Outlined.Done,
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(top = 15.dp),
                        onClick = {
                            if (port == "") {
                                navController.navigate("${AppNavRoute.ROUTE_TOOL}/${host}/5555") {
                                    popUpTo(AppNavRoute.ROUTE_MAIN)
                                }
                            } else if (port.toInt() !in 1..65535) {
                                fieldError = true
                            } else {
                                navController.navigate("${AppNavRoute.ROUTE_TOOL}/${host}/${port.toInt()}") {
                                    popUpTo(AppNavRoute.ROUTE_MAIN)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}