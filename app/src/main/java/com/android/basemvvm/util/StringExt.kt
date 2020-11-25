package com.android.basemvvm.util

fun Array<String>?.printStringFromArray(): String {
    if (this == null)
        return "{}"
    return (catchException {
        if (size == 0)
            return "{}"

        val sb = StringBuilder()
        sb.append("{ ")
        for (i in indices) {
            sb.append(this[i])
            if (i != size - 1)
                sb.append(", ")
        }
        sb.append(" }")
        sb.toString()
    } ?: "{}") as String
}

fun String?.isNotNullOrEmpty() = !isNullOrEmpty()