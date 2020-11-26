package com.android.basemvvm.data.remote

import com.android.basemvvm.util.logDebug
import okhttp3.Interceptor
import okhttp3.Response

class HttpLogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestTime = System.nanoTime()
        logDebug(
            "OkHttp",
            "Request ${request.method} ${request.url} \n"
                    + "Request Headers >> ${request.headers}\n"
                    + "Request Body >> ${request.body ?: ""}"
        )

        val response = chain.proceed(request)
        val responseTime = System.nanoTime()
        val body = response.peekBody(1024 * 1024)
        logDebug(
            "OkHttp",
            "Response ${request.method} ${request.url} ${((responseTime - requestTime) / 1e6).toInt()} ms\n"
                    + "Request Headers >> ${request.headers}\n"
                    + "Request Body >> ${request.body ?: ""}"
        )
        return response
    }

}