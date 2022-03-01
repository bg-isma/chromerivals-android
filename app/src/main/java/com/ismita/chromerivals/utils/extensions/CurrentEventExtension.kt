package com.ismita.chromerivals.utils.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.ismita.chromerivals.data.model.event.CurrentEvent
import com.ismita.chromerivals.utils.Constants
import com.ismita.chromerivals.utils.Utils
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object CurrentEventExtension {

    @RequiresApi(Build.VERSION_CODES.O)
    fun CurrentEvent.getEventMillisToFinish(): Long {
        var eventEndTime = this.endTime!!
        when {
            eventEndTime.contains("+") -> eventEndTime = eventEndTime.split("+")[0]
            eventEndTime.contains("-") -> eventEndTime = eventEndTime.split("-")[0]
        }

        val formatter = DateTimeFormatter.ofPattern(Constants.API_DATE_FORMAT)
        val finishTime = LocalDateTime.parse(eventEndTime, formatter)
            .atZone(ZoneId.of(Constants.API_ZONE_DATE_TIME))
            .withZoneSameInstant(ZonedDateTime.now().zone)

        return Utils.getMillisDifference(actualTime = ZonedDateTime.now(), finishTime = finishTime)
    }

}