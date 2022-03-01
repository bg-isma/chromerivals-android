package com.ismita.chromerivals.utils.extensions

import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent

object EventDBExtension {

    fun List<EventDB>.toUpcomingEvents(): List<UpcomingEvent> {
        return this.map { event ->
            return@map when {
                event.ani != null -> UpcomingEvent(ani = event.ani)
                event.bcu != null -> UpcomingEvent(bcu = event.bcu)
                else -> UpcomingEvent(
                    outpostName = event.outpostName,
                    deployTime = event.deployTime,
                    influence = event.influence,
                )
            }
        }
    }
}