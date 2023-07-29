package com.moling.micatoolkit.presentation.model

import android.content.SharedPreferences
import dadb.Dadb
import java.io.File

class Constants {
    companion object {
        var SharedPreferences: SharedPreferences? = null
        var adb: Dadb? = null
        var filesDir: File? = null
    }
}