/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.moling.micatoolkit.presentation.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DevicesOther
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.AppNavHost
import com.moling.micatoolkit.presentation.AppNavRoute
import com.moling.micatoolkit.presentation.Constants
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme

class MainActivity : ComponentActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var toast: Toast
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Constants.SharedPreferences = getSharedPreferences("MicaToolkit", MODE_PRIVATE)
        Constants.filesDir = filesDir
        context = applicationContext
        // 请求权限
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 222)
        // 直接在此处获取一个toast对象
        toast = Toast.makeText(applicationContext,"",Toast.LENGTH_SHORT)
        setContent {
            MicaToolkitTheme {
                AppNavHost()
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val emptyNavHostController = rememberSwipeDismissableNavController()
    MainAct(emptyNavHostController)
}

@Composable
fun MainAct(navController: NavHostController) {
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
                        text = stringResource(id = R.string.txt_selDevice),
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    Row {
                        IconButton(
                            imageVector = Icons.Outlined.Watch,
                            backgroundColor = MaterialTheme.colors.primary,
                            modifier = Modifier.padding(end = 5.dp),
                            onClick = {
                                navController.navigate("${AppNavRoute.ROUTE_PORT}/localhost")
                            }
                        )
                        IconButton(
                            imageVector = Icons.Outlined.DevicesOther,
                            backgroundColor = MaterialTheme.colors.secondary,
                            modifier = Modifier.padding(start = 5.dp),
                            onClick = {
                                navController.navigate(AppNavRoute.ROUTE_TARGET)
                            }
                        )
                    }
                }
                IconButton(
                    imageVector = Icons.Outlined.Help,
                    backgroundColor = Color.Transparent,
                    onClick = { /* TODO: Help Activity */}
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