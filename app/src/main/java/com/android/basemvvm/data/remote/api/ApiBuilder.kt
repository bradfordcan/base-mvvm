package com.android.basemvvm.data.remote.api

import com.android.basemvvm.util.javaClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit configuration with Moshi adapter
 */
abstract class ApiBuilder(open var baseUrl: String) {
    open fun getConnectionTimeout() = 30L
    open fun getReadTimeout() = 60L
    open fun getWriteTimeout() = 60L

    open fun getClientBuilder() = OkHttpClient.Builder().apply {
        connectTimeout(getConnectionTimeout(), TimeUnit.SECONDS)
        readTimeout(getReadTimeout(), TimeUnit.SECONDS)
        writeTimeout(getWriteTimeout(), TimeUnit.SECONDS)

        addNetworkInterceptor(HttpLoggingInterceptor())
    }

    open fun createDefaultService(): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(getClientBuilder().build()).build()

    inline fun <reified T> createServiceByAdapter(moshi: Moshi? = null): T {
        val moshiBuilder = Moshi.Builder()
        val moshiFinal = moshi ?: moshiBuilder.add(KotlinJsonAdapterFactory()).build()
        return createDefaultService().newBuilder()
            .addConverterFactory(MoshiConverterFactory.create(moshiFinal)).build()
            .create(javaClass<T>())
    }
}