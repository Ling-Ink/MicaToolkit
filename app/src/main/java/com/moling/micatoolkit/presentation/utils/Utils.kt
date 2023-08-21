package com.moling.micatoolkit.presentation.utils

import android.widget.Toast
import com.moling.micatoolkit.presentation.activities.MainActivity
import com.moling.micatoolkit.presentation.Constants

fun String.showToast(duration: Int = Toast.LENGTH_SHORT){
    MainActivity .toast.setText(this)
    MainActivity .toast.duration = duration
    MainActivity .toast.show()
}

const val SUFFIX_IMAGE = "bmp|gif|jpg|jpeg|mpeg|png|tif|tiff"
const val SUFFIX_APK = "apk"
fun getFileType(fileName: String): Int {
    return when (fileName.split(".").last()) {
        in SUFFIX_IMAGE.split("|") -> {
            Constants.TYPE_IMAGE
        }
        SUFFIX_APK -> {
            Constants.TYPE_APK
        }
        else -> {
            Constants.TYPE_OTHER
        }
    }
}