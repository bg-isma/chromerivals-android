package com.ismita.chromerivals.services.domain.events

import com.ismita.chromerivals.models.event.EventDB
import com.ismita.chromerivals.models.event.UpcomingEvent
import com.ismita.chromerivals.models.serviceResponse.EventsResponse
import com.ismita.chromerivals.services.repositories.ChromeRivalsEventRepository
import com.ismita.chromerivals.services.repositories.ChromeRivalsRepository
import com.ismita.chromerivals.utils.extensions.AnyExtension.toUpcomingEvent
import javax.inject.Inject

class GetMothershipsEventsUseCase @Inject constructor(
    private val repository: ChromeRivalsRepository,
    private val eventsRepository: ChromeRivalsEventRepository,
) {
    suspend operator fun invoke(): List<UpcomingEvent> = getMothershipsEvent()

    private suspend fun getMothershipsEvent(): List<UpcomingEvent> {
        val mothershipsEventResponse = repository.getMothershipsEvent()
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
        return eventsRepository.getAllMotherships()
    }

    private suspend fun deleteDatabaseMothershipsIfAny() {
        eventsRepository.deleteAllMotherships()
    }

    private suspend fun insertNewDatabaseMotherships(eventsResponse: EventsResponse) {
        eventsRepository.insertEvents(anyResultToEventDB(eventsResponse))
    }

}