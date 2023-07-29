package com.moling.micatoolkit.presentation.utils

import android.widget.Toast
import com.moling.micatoolkit.presentation.MainActivity

fun String.showToast(duration: Int = Toast.LENGTH_SHORT){
    MainActivity .toast.setText(this)
    MainActivity .toast.duration = duration
    MainActivity .toast.show()
}