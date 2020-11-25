package com.android.basemvvm.util

inline fun <T> catchException(tryBlock: () -> T? = { null }) =
    try {
        tryBlock()
    } catch (e: Exception) {
        e.printStackTrace()
    }
