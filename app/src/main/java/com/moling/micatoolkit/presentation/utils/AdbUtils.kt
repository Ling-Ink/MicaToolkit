package com.moling.micatoolkit.presentation.utils

import dadb.AdbShellResponse
import dadb.Dadb
import java.io.File
import java.io.IOException

fun Dadb.execShell(command: String): AdbShellResponse {
    var execResult: AdbShellResponse? = null
    val thread = Thread {
        execResult = this.shell(command)
    }
    thread.start()
    thread.join()
    return requireNotNull(execResult)
}

fun Dadb.installApk(apkFile: File): String {
    var execResult = ""
    val thread = Thread {
        try {
            this.install(apkFile)
        } catch (e: IOException) {
            execResult = e.toString()
        }
    }
    thread.start()
    thread.join()
    return execResult
}

fun Dadb.uninstallApk(packageName: String): String {
    var execResult = ""
    val thread = Thread {
        try {
            this.uninstall(packageName)
        } catch (e: IOException) {
            execResult = e.toString()
        }
    }
    thread.start()
    thread.join()
    return execResult
}

fun Dadb.pushFile(srcFile: File, remotePath: String): String {
    var execResult = ""
    val thread = Thread {
        try {
            this.push(srcFile, remotePath)
        } catch (e: IOException) {
            execResult = e.toString()
        }
    }
    thread.start()
    thread.join()
    return execResult
}

fun Dadb.pullFile(dstFile: File, remotePath: String): String {
    var execResult = ""
    val thread = Thread {
        try {
            this.pull(dstFile, remotePath)
        } catch (e: IOException) {
            execResult = e.toString()
        }
    }
    thread.start()
    thread.join()
    return execResult
}