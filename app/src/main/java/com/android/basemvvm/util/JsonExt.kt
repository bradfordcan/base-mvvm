package com.android.basemvvm.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun ofMoshi(factory: JsonAdapter.Factory? = null): Moshi =
    Moshi.Builder().apply {
        if(factory != null) {
            add(factory)
        }
        add(KotlinJsonAdapterFactory())
    }.build()


inline fun <reified T> String?.fromJson(): T? {
    if(this == null) {
        return null
    }
    return ofMoshi().adapter(javaClass<T>()).fromJson(this)
}

inline fun <reified T> T?.toJson(): String {
    if (this == null) return "{}"
    return ofMoshi().adapter(javaClass<T>()).toJson(this) ?: "{}"
}