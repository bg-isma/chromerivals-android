package com.ismita.chromerivals.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.ismita.chromerivals.utils.extensions.LongExtension.hourToSeconds
import com.ismita.chromerivals.utils.extensions.LongExtension.minutesToSeconds
import com.ismita.chromerivals.utils.extensions.LongExtension.secondsToMilliSeconds
import java.time.ZonedDateTime

object Utils {

    fun <T> concatenate(vararg lists: List<T>): List<T> {
        return listOf(*lists).flatten()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMillisDifference(actualTime: ZonedDateTime, finishTime: ZonedDateTime): Long {
        var seconds = 0L
        seconds += (finishTime.hour - actualTime.hour).toLong().hourToSeconds()
        seconds += (finishTime.minute - actualTime.minute).toLong().minutesToSeconds()
        seconds += (finishTime.second - actualTime.second).toLong()
        return seconds.secondsToMilliSeconds()
    }

}