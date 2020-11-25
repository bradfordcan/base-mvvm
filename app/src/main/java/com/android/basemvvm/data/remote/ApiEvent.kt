package com.android.basemvvm.data.remote

import android.content.res.Resources
import com.android.basemvvm.data.remote.ApiEvent.ApiStatus

sealed class ApiEvent<T> {

    abstract val data: T?

    sealed class ApiStatus {
        data class Loading(override val data: Nothing? = null) : ApiEvent<Nothing>()

        data class Success(override val data: ApiResponse<*>) : ApiEvent<ApiResponse<*>>()

        data class Error(override val data: ApiResponse<*>) : ApiEvent<ApiResponse<*>>()

        data class Cancellation(override val data: Nothing? = null) : ApiEvent<Nothing>() {
            fun title(resources: Resources) = "Canceled"
            fun message(resources: Resources) = "The action was canceled."
        }
    }

}

fun ApiEvent<*>?.isLoading() = this is ApiStatus.Loading
fun ApiEvent<*>?.isSuccess() = this is ApiStatus.Success
fun ApiEvent<*>?.isError() = this is ApiStatus.Error

