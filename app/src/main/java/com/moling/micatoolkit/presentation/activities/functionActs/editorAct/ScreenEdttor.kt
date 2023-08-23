package com.moling.micatoolkit.presentation.activities.functionActs.editorAct

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
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.activities.functionActs.IconButton
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import com.moling.micatoolkit.presentation.utils.adbExec
import com.moling.micatoolkit.presentation.utils.isNumeric

class PortActivity : ComponentActivity()

@Composable
fun ScreenEditorAct(navController: NavHostController) {
    var width by remember {  mutableStateOf("") }
    var widthFieldError by remember { mutableStateOf(false) }
    var height by remember {  mutableStateOf("") }
    var heightFieldError by remember { mutableStateOf(false) }
    var density by remember {  mutableStateOf("") }
    var densityFieldError by remember { mutableStateOf(false) }
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
                    when (global.getString("screenEditor")) {
                        Constants.EDITOR_SCREEN_SIZE -> {
                            TransparentTextField(
                                value = width,
                                placeholder = stringResource(id = R.string.editor_screen_size_width),
                                onValueChange = { width = it },
                                isError = widthFieldError
                            )
                            TransparentTextField(
                                value = height,
                                placeholder = stringResource(id = R.string.editor_screen_size_height),
                                onValueChange = { height = it },
                                isError = heightFieldError
                            )
                        }
                        Constants.EDITOR_SCREEN_DENSITY -> {
                            TransparentTextField(
                                value = density,
                                placeholder = stringResource(id = R.string.editor_screen_density_value),
                                onValueChange = { density = it },
                                isError = densityFieldError
                            )
                        }
                    }
                    IconButton(
                        imageVector = Icons.Outlined.Done,
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(top = 15.dp),
                        onClick = {
                            when (global.getString("screenEditor")) {
                                Constants.EDITOR_SCREEN_SIZE -> {
                                    if (width.isEmpty() or !isNumeric(width)) widthFieldError = true
                                    if (height.isEmpty() or !isNumeric(height)) heightFieldError = true
                                    if (width.isNotEmpty() and isNumeric(width) and height.isNotEmpty() and isNumeric(height)) {
                                        adbExec("wm size ${width}x${height}")
                                        navController.popBackStack()
                                    }
                                }
                                Constants.EDITOR_SCREEN_DENSITY -> {
                                    if (density.isEmpty() or !isNumeric(density)) densityFieldError = true
                                    if (density.isNotEmpty() and isNumeric(density)) {
                                        adbExec("wm density $density")
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TransparentTextField(
    value: String,
    isError: Boolean,
    placeholder: String,
    onValueChange: (value: String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = MaterialTheme.colors.surface) },
        textStyle = TextStyle(
            color = Color.White
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        isError = isError
    )
}