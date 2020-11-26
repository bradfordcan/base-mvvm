package com.android.basemvvm.data.remote

import android.content.res.Resources
import com.android.basemvvm.data.remote.exceptions.NetworkConnectionException

sealed class ApiResponse<T>(open val code: Int = 0, open var data: T? = null) {

    class Cancellation : ApiResponse<Nothing>()

    class Success<T>(override var data: T?) : ApiResponse<T>(HttpCodes.OK.code, data)

    /**
     * Basic error response in REST API
     */
    open class Error<T>(
        override var data: T? = null,
        open var exception: Throwable? = null,
        override val code: Int = HttpCodes.UNKNOWN.code
    ) : ApiResponse<T>(code, data)

    /**
     * HTTP response in REST API
     */
    class HttpError(
        override var code: Int = 0,
        override var data: SimpleResponse? = null,
        override var exception: Throwable? = null
    ) : Error<SimpleResponse>()

    class ConnectionError : Error<Nothing>(exception = NetworkConnectionException())

    class NetworkError : Error<Nothing>(exception = NetworkConnectionException())

}

fun ApiResponse<*>?.createError(resources: Resources): UiError {
    fun unexpected(code: Int) = UiError(
        icon = 0,
        title = "Unexpected Error!",
        message = "An unexpected error occurred. (code = %d".format(code)
    )

    return when {
        else -> unexpected(-500)
    }
}