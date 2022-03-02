package com.ismita.chromerivals.data.service.database.repositories.event

import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent

interface CREventRoomRepositoryInterface {
    suspend fun getAllMotherships(): List<UpcomingEvent>
    suspend fun getAllOutposts(): List<UpcomingEvent>
    suspend fun deleteAllOutposts()
    suspend fun deleteAllMotherships()
    suspend fun insertEvents(events: List<EventDB>)
}