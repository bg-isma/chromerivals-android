package com.ismita.chromerivals.domain.events

import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent
import com.ismita.chromerivals.data.model.responses.EventsResponse
import com.ismita.chromerivals.data.service.api.repositories.event.CREventRepositoryInterface
import com.ismita.chromerivals.data.service.database.repositories.event.CREventRoomRepositoryInterface
import com.ismita.chromerivals.utils.extensions.AnyExtension.toUpcomingEvent
import javax.inject.Inject

class GetMothershipsEventsUseCase @Inject constructor(
    private val eventRepository: CREventRepositoryInterface,
    private val roomEventRepository: CREventRoomRepositoryInterface,
) {
    suspend operator fun invoke(): List<UpcomingEvent> = getMothershipsEvent()

    private suspend fun getMothershipsEvent(): List<UpcomingEvent> {
        val mothershipsEventResponse = eventRepository.getMothershipsEvent()
        return if (mothershipsEventResponse.result != null) {
            deleteDatabaseMothershipsIfAny()
            insertNewDatabaseMotherships(mothershipsEventResponse)
            anyResultToUpcomingEvent(mothershipsEventResponse)
        } else getFromDataBaseIfResponseEmpty()
    }

    private fun anyResultToUpcomingEvent(eventsResponse: EventsResponse): List<UpcomingEvent> {
        val mothershipsFormatted = eventsResponse.result?.toUpcomingEvent()
        return if (mothershipsFormatted != null) {
            listOf(UpcomingEvent(ani = mothershipsFormatted.ani), UpcomingEvent(bcu = mothershipsFormatted.bcu))
        } else emptyList()
    }

    private fun anyResultToEventDB(eventsResponse: EventsResponse): List<EventDB> {
        val mothershipsFormatted = eventsResponse.result?.toUpcomingEvent()
        return if (mothershipsFormatted != null) {
            listOf(EventDB(ani = mothershipsFormatted.ani), EventDB(bcu = mothershipsFormatted.bcu))
        } else emptyList()
    }

    private suspend fun getFromDataBaseIfResponseEmpty():List<UpcomingEvent> {
        return roomEventRepository.getAllMotherships()
    }

    private suspend fun deleteDatabaseMothershipsIfAny() {
        roomEventRepository.deleteAllMotherships()
    }

    private suspend fun insertNewDatabaseMotherships(eventsResponse: EventsResponse) {
        roomEventRepository.insertEvents(anyResultToEventDB(eventsResponse))
    }

}