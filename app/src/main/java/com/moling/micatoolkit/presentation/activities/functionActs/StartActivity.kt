package com.moling.micatoolkit.presentation.activities.functionActs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DevicesOther
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.navigator.navToInfoAct
import com.moling.micatoolkit.presentation.navigator.navToPortAct
import com.moling.micatoolkit.presentation.navigator.navToTargetAct
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme

@Composable
fun StartAct(navController: NavHostController) {
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
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 25.dp)) {
                    Text(
                        text = stringResource(id = R.string.startAct_SelDevice),
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    Row {
                        IconButton(
                            imageVector = Icons.Outlined.Watch,
                            backgroundColor = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(end = 5.dp),
                            onClick = {
                                navToPortAct(navController, "localhost")
                            }
                        )
                        IconButton(
                            imageVector = Icons.Outlined.DevicesOther,
                            backgroundColor = MaterialTheme.colors.secondary,
                            modifier = Modifier.padding(start = 5.dp),
                            onClick = {
                                navToTargetAct(navController)
                            }
                        )
                    }
                }
                IconButton(
                    imageVector = Icons.Outlined.Info,
                    backgroundColor = Color.Transparent,
                    onClick = { navToInfoAct(navController) }
                )
            }
        }
    }
}

@Composable
fun IconButton(imageVector: ImageVector, backgroundColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = backgroundColor
        ),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
fun IconButton(imageVector: ImageVector, modifier: Modifier, backgroundColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = backgroundColor
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.White
        )
    }
}