package com.ismita.chromerivals.utils.extensions

import kotlin.Long

object LongExtension {

    fun Long.hourToSeconds(): Long {
        return this * 3600
    }

    fun Long.minutesToSeconds(): Long {
        return this * 60
    }

    fun Long.secondsToMilliSeconds(): Long {
        return this * 1000
    }

}