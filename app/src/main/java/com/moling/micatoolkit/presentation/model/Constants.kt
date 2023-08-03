package com.moling.micatoolkit.presentation.model

import android.content.SharedPreferences
import dadb.Dadb
import java.io.File

class Constants {
    companion object {
        var SharedPreferences: SharedPreferences? = null
        var adb: Dadb? = null
        var filesDir: File? = null

        const val TYPE_NO_PERM = -1
        const val TYPE_OTHER = 0
        const val TYPE_DIRECTORY = 1
        const val TYPE_APK = 2
        const val TYPE_IMAGE = 3
    }
}