package com.moling.micatoolkit.presentation.utils

import dadb.AdbStream
import dadb.AdbSyncStream
import dadb.Dadb
import okio.Source
import okio.source
import java.io.File

class DadbExternal : Dadb {
    override fun close() {
        this.close()
    }

    override fun open(destination: String): AdbStream {
        return this.open(destination)
    }

    override fun supportsFeature(feature: String): Boolean {
        return this.supportsFeature(feature)
    }

    override fun push(src: File, remotePath: String, mode: Int, lastModifiedMs: Long) {
        push(src.source(), remotePath, mode, lastModifiedMs)
    }

    override fun push(source: Source, remotePath: String, mode: Int, lastModifiedMs: Long) {
        openSync().use { stream ->
            stream.send(source, remotePath, mode, lastModifiedMs)
        }
    }

    override fun openSync(): AdbSyncStream {
        val stream = open("sync:")
        return AdbSyncStream(stream)
    }
}