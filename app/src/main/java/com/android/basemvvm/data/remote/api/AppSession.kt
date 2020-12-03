package com.android.basemvvm.data.remote.api

import android.os.Build
import com.android.basemvvm.util.getDeviceModel

class AppSession (
    var deviceModel: String = getDeviceModel(),
    var deviceOs: String = "android",
    var deviceVersionName: String = Build.VERSION.CODENAME,
    var deviceVersionCode: Int = Build.VERSION.SDK_INT,
    var appVersionName: String = "0.0.0",
    var appVersionCode: Int = 0,
    var uuid: String = "",
    var notificationToken: String = ""
)