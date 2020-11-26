package com.android.basemvvm.util

import timber.log.Timber

fun logDebug(tag: String = "", message: Any?, throwable: Throwable? = null) {
    Timber.d(throwable, message.toString())
}

fun logError(message: Any?, throwable: Throwable? = null) {
    Timber.e(throwable, message.toString())
}