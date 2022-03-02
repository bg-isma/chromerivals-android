package com.ismita.chromerivals.domain.events

import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent
import com.ismita.chromerivals.data.model.responses.EventsResponse
import com.ismita.chromerivals.data.service.api.repositories.event.CREventRepositoryInterface
import com.ismita.chromerivals.data.service.database.repositories.event.CREventRoomRepositoryInterface
import com.ismita.chromerivals.utils.extensions.AnyExtension.toEventDBList
import com.ismita.chromerivals.utils.extensions.AnyExtension.toUpcomingEventList
import javax.inject.Inject

class GetOutpostsEventsUseCase @Inject constructor(
    private val eventRepository: CREventRepositoryInterface,
    private val roomEventRepository: CREventRoomRepositoryInterface,
) {
    suspend operator fun invoke(): List<UpcomingEvent> = getOutpostsEvents()

    private suspend fun getOutpostsEvents(): List<UpcomingEvent> {
        val outpostsResponse = eventRepository.getOutpostsEvents()
        return if (outpostsResponse.result != null) {
            deleteDatabaseOutpostsIfAny()
            insertNewDatabaseOutposts(outpostsResponse)
            anyResultToUpcomingEvent(outpostsResponse)
        } else getFromDataBaseIfResponseEmpty()
    }

    private suspend fun getFromDataBaseIfResponseEmpty():List<UpcomingEvent> {
        return roomEventRepository.getAllOutposts()
    }

    private fun anyResultToUpcomingEvent(eventsResponse: EventsResponse): List<UpcomingEvent> {
        return eventsResponse.result?.toUpcomingEventList() ?: emptyList()
    }

    private fun anyResultToEventDB(eventsResponse: EventsResponse): List<EventDB> {
        return eventsResponse.result?.toEventDBList() ?: emptyList()
    }

    private suspend fun deleteDatabaseOutpostsIfAny() {
        roomEventRepository.deleteAllOutposts()
    }

    private suspend fun insertNewDatabaseOutposts(eventsResponse: EventsResponse) {
        roomEventRepository.insertEvents(anyResultToEventDB(eventsResponse))
    }

}