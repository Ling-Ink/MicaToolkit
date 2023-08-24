/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.moling.micatoolkit.presentation.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.moling.micatoolkit.presentation.Global
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.navigator.NavHost
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme
import dadb.AdbKeyPair
import java.io.File
import java.io.FileNotFoundException


class MainActivity : ComponentActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var toast: Toast
        lateinit var global: Global
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // 初始化全局变量
        global = Global()
        global.set("SharedPreferences", getSharedPreferences("MicaToolkit", MODE_PRIVATE))
        // 获取程序版本
        try {
            val info = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
            global.set("versionName", info.versionName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        // 加载 AdbKeyPair
        genKeyPair(filesDir)
        context = applicationContext
        // 请求权限
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 222)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 222)
        // 直接在此处获取一个toast对象
        toast = Toast.makeText(applicationContext,"", Toast.LENGTH_SHORT)
        setContent {
            MicaToolkitTheme {
                NavHost()
            }
        }
        // Microsoft App Center Analytics
        AppCenter.start(
            application, "ccc2e6fe-3f89-4796-b800-8800bc7e3652",
            Analytics::class.java, Crashes::class.java
        )
    }
}

fun genKeyPair(filesDir: File) {
    val ADB_KEY_PATH = filesDir.absolutePath
    val keyPair: AdbKeyPair
    global.set(
        "keyPair",
        try {
            keyPair = AdbKeyPair.read(
                File("${ADB_KEY_PATH}/adbkey"),
                File("${ADB_KEY_PATH}/adbkey.pub")
            )
            keyPair
        } catch (e: FileNotFoundException) {
            AdbKeyPair.generate(
                File("${ADB_KEY_PATH}/adbkey"),
                File("${ADB_KEY_PATH}/adbkey.pub")
            )
            genKeyPair(filesDir)
        }
    )
}