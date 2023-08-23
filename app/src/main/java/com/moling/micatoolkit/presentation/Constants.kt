package com.moling.micatoolkit.presentation

import dadb.Dadb

class Constants {
    companion object Const {
        var adb: Dadb? = null

        const val NO_PERM = -1
        const val OTHER = 0
        const val DIRECTORY = 1
        const val APK = 2
        const val IMAGE = 3

        const val FILE_SOURCE_LOCAL = "local"
        const val FILE_SOURCE_REMOTE = "remote"
    }
}