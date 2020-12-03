package com.android.basemvvm.data.remote

import android.content.Context
import com.android.basemvvm.data.remote.exceptions.NetworkConnectionException
import com.android.basemvvm.util.hasNetwork
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnline())
            throw NetworkConnectionException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        return appContext.hasNetwork(appContext)
    }

}