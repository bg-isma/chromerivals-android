package com.ismita.chromerivals.utils.extensions

import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent

object UpcomingEventExtension {

    fun List<UpcomingEvent>.toEventDB(): List<EventDB> {
        return this.map { event ->
            return@map when {
                event.ani != null -> EventDB(ani = event.ani)
                event.bcu != null -> EventDB(bcu = event.bcu)
                else -> EventDB(
                    outpostName = event.outpostName,
                    deployTime = event.deployTime,
                    influence = event.influence,
                )
            }
        }
    }

}