package com.ismita.chromerivals.domain.events

import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.event.UpcomingEvent
import com.ismita.chromerivals.data.model.responses.EventsResponse
import com.ismita.chromerivals.data.service.database.repositories.event.ChromeRivalsEventRepository
import com.ismita.chromerivals.data.service.api.repositories.event.ChromeRivalsRepository
import com.ismita.chromerivals.utils.extensions.AnyExtension.toEventDBList
import com.ismita.chromerivals.utils.extensions.AnyExtension.toUpcomingEventList
import javax.inject.Inject

class GetOutpostsEventsUseCase @Inject constructor(
    private val repository: ChromeRivalsRepository,
    private val eventsRepository: ChromeRivalsEventRepository,
) {
    suspend operator fun invoke(): List<UpcomingEvent> = getOutpostsEvents()

    private suspend fun getOutpostsEvents(): List<UpcomingEvent> {
        val outpostsResponse = repository.getOutpostsEvents()
        return if (outpostsResponse.result != null) {
            deleteDatabaseOutpostsIfAny()
            insertNewDatabaseOutposts(outpostsResponse)
            anyResultToUpcomingEvent(outpostsResponse)
        } else getFromDataBaseIfResponseEmpty()
    }

    private suspend fun getFromDataBaseIfResponseEmpty():List<UpcomingEvent> {
        return eventsRepository.getAllOutposts()
    }

    private fun anyResultToUpcomingEvent(eventsResponse: EventsResponse): List<UpcomingEvent> {
        return eventsResponse.result?.toUpcomingEventList() ?: emptyList()
    }

    private fun anyResultToEventDB(eventsResponse: EventsResponse): List<EventDB> {
        return eventsResponse.result?.toEventDBList() ?: emptyList()
    }

    private suspend fun deleteDatabaseOutpostsIfAny() {
        eventsRepository.deleteAllOutposts()
    }

    private suspend fun insertNewDatabaseOutposts(eventsResponse: EventsResponse) {
        eventsRepository.insertEvents(anyResultToEventDB(eventsResponse))
    }

}