package com.android.basemvvm.data.remote

import com.android.basemvvm.util.isNotNullOrEmpty
import com.squareup.moshi.Json

data class SimpleResponse(
    @Json(name = "status") var status: Int = 0,
    @Json(name = "message") var message: String? = null,
    @Json(name ="body") var body: String? = null,
    @Json(name = "name") var name: String? = null
) {
    val messageIfExist: String?
        get() = when {
            message.isNotNullOrEmpty() -> message
            name.isNotNullOrEmpty() -> name
            body.isNotNullOrEmpty() -> body
            else -> null
        }
}