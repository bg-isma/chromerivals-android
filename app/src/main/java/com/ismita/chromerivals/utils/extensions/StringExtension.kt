package com.ismita.chromerivals.utils.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.ismita.chromerivals.utils.Constants
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object StringExtension {

    fun String.deleteStrangeCharacters(): String {
        var result = this
        val charactersToDelete = "\\o,\\y,\\c,\\g,\\e,\\r,\\e,\\m,\\u,\\I,\\r,\\l,\\i,\\L".split(",")
        for (letter in charactersToDelete) {
            result = result.replace(letter, "")
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun String.toLocalZoneDateFormatted(): String {
        val formatter = DateTimeFormatter.ofPattern(Constants.API_DATE_FORMAT)
        val serverDate = LocalDateTime.parse(this, formatter).atZone(ZoneId.of(Constants.API_ZONE_DATE_TIME))
        val localDate = serverDate.withZoneSameInstant(ZonedDateTime.now().zone)
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }

}