package com.moling.micatoolkit.presentation.activities.functionActs

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.moling.micatoolkit.presentation.navigator.navToToolAct
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme

class PortActivity : ComponentActivity()

@Composable
fun PortAct(navController: NavHostController) {
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
                        placeholder = { Text(text = stringResource(id = R.string.portAct_RemotePort), color = MaterialTheme.colors.surface) },
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
                                navToToolAct(navController, 5555)
                            } else if (port.toInt() !in 1..65535) {
                                fieldError = true
                            } else {
                                navToToolAct(navController, port.toInt())
                            }
                        }
                    )
                }
            }
        }
    }
}