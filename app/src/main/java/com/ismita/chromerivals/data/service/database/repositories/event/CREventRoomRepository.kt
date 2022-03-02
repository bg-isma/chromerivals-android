package com.ismita.chromerivals.data.service.database.repositories.event

import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsEventRoom
import com.ismita.chromerivals.utils.extensions.EventDBExtension.toUpcomingEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CREventRoomRepository @Inject constructor(
    private val eventRoom: ChromeRivalsEventRoom,
): CREventRoomRepositoryInterface {

    override suspend fun getAllMotherships(): List<UpcomingEvent> {
        return withContext(Dispatchers.IO) {
            eventRoom.getAllMotherships().toUpcomingEvents()
        }
    }

    override suspend fun getAllOutposts(): List<UpcomingEvent> {
        return withContext(Dispatchers.IO) {
            eventRoom.getAllOutposts().toUpcomingEvents()
        }
    }

    override suspend fun deleteAllOutposts() {
        return withContext(Dispatchers.IO) {
            eventRoom.deleteAllOutpost()
        }
    }

    override suspend fun deleteAllMotherships() {
        return withContext(Dispatchers.IO) {
            eventRoom.deleteAllMotherships()
        }
    }

    override suspend fun insertEvents(events: List<EventDB>) {
        return withContext(Dispatchers.IO) {
            eventRoom.insertEvents(events)
        }
    }

}