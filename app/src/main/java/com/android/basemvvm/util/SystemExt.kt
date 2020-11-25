package com.android.basemvvm.util

import android.os.Build

fun getDeviceModel(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL ?: return manufacturer ?: "Unknown Device"
    return if (model.startsWith(manufacturer)) {
        // model.capitalizeFirst()
        model
    } else {
        model
    }
}