package com.moling.micatoolkit.presentation.activities.functionActs.fileAct

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
import com.moling.micatoolkit.presentation.utils.execShell

@Composable
fun CreateFolderAct(navController: NavHostController) {
    var folderName by remember {  mutableStateOf("") }
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
                        value = folderName,
                        onValueChange = { folderName = it },
                        placeholder = { Text(text = stringResource(id = R.string.create_folderName), color = MaterialTheme.colors.surface) },
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
                            if (folderName == "") {
                                fieldError = true
                            } else {
                                requireNotNull(Constants.adb).execShell("mkdir ${global.getString("folderCreateLocation")}/${folderName}")
                                navController.popBackStack()
                            }
                        }
                    )
                }
            }
        }
    }
}