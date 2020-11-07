package com.android.basemvvm.util

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double?.roundToDecPlaces(places: Int = 0): Double {
    if(this == null)
        return 0.0

    var value = this
    require(places >= 0)

    val factor = 10.0.pow(places.toDouble()).toLong()
    value *= factor

    return value.roundToInt().toDouble() / factor
}