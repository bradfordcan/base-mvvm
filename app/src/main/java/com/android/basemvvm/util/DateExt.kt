package com.android.basemvvm.util

import android.content.Context
import com.android.basemvvm.empty.R
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(dateFormat: String = "yyyy-MM-dd HH:mm:ss"): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.US)
    parser.timeZone = TimeZone.getTimeZone("UTC")
    return parser.parse(this)!!
}

fun Date.formatTo(dateFormat: String): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.US)
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    return formatter.format(this)
}

fun currentDate(): Date {
    val calendar = Calendar.getInstance()
    return calendar.time
}

fun Int.toTwoDigit(): String {
    return if (this < 10) "0$this" else toString()
}

fun Context?.toTwelveHour(timeStamp: Long): String {
    if (this == null)
        return ""
    val calendar = Calendar.getInstance().apply { timeInMillis = timeStamp }
    var timeHour = calendar.get(Calendar.HOUR)
    val timeMinutes = calendar.get(Calendar.MINUTE).toTwoDigit()
    val timeAmPm = calendar.get(Calendar.AM_PM)
    val timeAmText = getString(R.string.time_am)
    val timePmText = getString(R.string.time_pm)
    return if (timeAmPm == Calendar.AM) {
        "$timeHour:$timeMinutes $timeAmText"
    } else {
        if (timeHour == 0) {
            timeHour = 12
        }
        "$timeHour:$timeMinutes $timePmText"
    }
}