package com.android.basemvvm.data.remote.api

enum class HttpCodes(val code: Int) {
    UNKNOWN(0),
    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    INTERNAL_SERVER_ERROR(501)
}