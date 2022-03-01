package com.ismita.chromerivals.services.repositories

import com.ismita.chromerivals.models.event.EventDB
import com.ismita.chromerivals.models.event.UpcomingEvent
import com.ismita.chromerivals.services.database.ChromeRivalsEventRoom
import com.ismita.chromerivals.utils.extensions.EventDBExtension.toUpcomingEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChromeRivalsEventRepository @Inject constructor(
    private val eventRoom: ChromeRivalsEventRoom,
) {

    suspend fun getAllMotherships(): List<UpcomingEvent> {
        return withContext(Dispatchers.IO) {
            eventRoom.getAllMotherships().toUpcomingEvents()
        }
    }

    suspend fun getAllOutposts(): List<UpcomingEvent> {
        return withContext(Dispatchers.IO) {
            eventRoom.getAllOutposts().toUpcomingEvents()
        }
    }

    suspend fun deleteAllOutposts() {
        return withContext(Dispatchers.IO) {
            eventRoom.deleteAllOutpost()
        }
    }

    suspend fun deleteAllMotherships() {
        return withContext(Dispatchers.IO) {
            eventRoom.deleteAllMotherships()
        }
    }

    suspend fun insertEvents(events: List<EventDB>) {
        return withContext(Dispatchers.IO) {
            eventRoom.insertEvents(events)
        }
    }

}