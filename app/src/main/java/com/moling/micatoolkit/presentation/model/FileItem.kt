package com.moling.micatoolkit.presentation.model

class FileItem(fileName: String, fileType: Int) {
    val fileName: String
    val fileType: Int

    init {
        this.fileName = fileName
        this.fileType = fileType
    }
}