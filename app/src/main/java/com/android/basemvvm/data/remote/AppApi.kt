package com.android.basemvvm.data.remote

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class AppApi(open var baseUrl: String) {
    open fun getConnectionTimeout() = 30L
    open fun getReadTimeout() = 60L
    open fun getWriteTimeout() = 60L

    open fun getClientBuilder() = OkHttpClient.Builder().apply {
        connectTimeout(getConnectionTimeout(), TimeUnit.SECONDS)
    }
}