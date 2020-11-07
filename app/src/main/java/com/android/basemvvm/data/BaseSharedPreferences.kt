package com.android.basemvvm.data

import `in`.co.ophio.secure.core.KeyStoreKeyGenerator
import `in`.co.ophio.secure.core.ObscuredPreferencesBuilder
import android.app.Application
import android.content.SharedPreferences
import com.android.basemvvm.util.fromJson
import com.android.basemvvm.util.toJson

class BaseSharedPreferences(
    application: Application,
    settingName: String? = null,
    isEnabledObfuscated: Boolean = true,
    key: String = KeyStoreKeyGenerator.get(application, application.packageName).loadOrGenerateKeys(),
    val sharedPreference: SharedPreferences = ObscuredPreferencesBuilder()
    .setApplication(application)
    .obfuscateValue(isEnabledObfuscated)
    .obfuscateKey(isEnabledObfuscated)
    .setSharePrefFileName(settingName)
    .setSecret(key)
    .createSharedPrefs()
) {
    inline fun <reified T> get(key: String, def: T): T {
        return when (def) {
            is Boolean -> sharedPreference.getBoolean(key, def) as T
            is Int -> sharedPreference.getInt(key, def) as T
            is Long -> sharedPreference.getLong(key, def) as T
            is Float -> sharedPreference.getFloat(key, def) as T
            is Double -> sharedPreference.getFloat(key, def.toFloat()).toDouble() as T
            is String -> sharedPreference.getString(key, def) as T
            else -> sharedPreference.getString(key, "").fromJson() ?: def
        }
    }

    fun put(key: String, value: Any?) {
        when (value) {
            is Boolean -> sharedPreference.edit().putBoolean(key, value).apply()
            is Int -> sharedPreference.edit().putInt(key, value).apply()
            is Long -> sharedPreference.edit().putLong(key, value).apply()
            is Float -> sharedPreference.edit().putFloat(key, value).apply()
            is Double -> sharedPreference.edit().putFloat(key, value.toFloat()).apply()
            is String -> sharedPreference.edit().putString(key, value).apply()
            else -> sharedPreference.edit().putString(key, value.toJson()).apply()
        }
    }
}