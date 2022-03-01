package com.ismita.chromerivals.services.domain.events

import com.google.gson.internal.LinkedTreeMap
import com.ismita.chromerivals.models.event.CurrentEvent
import com.ismita.chromerivals.models.serviceResponse.EventsResponse
import com.ismita.chromerivals.services.repositories.ChromeRivalsRepository
import com.ismita.chromerivals.utils.extensions.AnyExtension.toCurrentEvent
import java.util.ArrayList
import javax.inject.Inject

class GetCurrentEventsUseCase @Inject constructor(
    private val repository: ChromeRivalsRepository
) {
    suspend operator fun invoke(): List<CurrentEvent> = getCurrentEvents()

    private suspend fun getCurrentEvents(): List<CurrentEvent> {
        val currentEventResponse = repository.getCurrentEvents()
        return anyResultToCurrentEvents(currentEventResponse)
    }

    private fun anyResultToCurrentEvents(eventsResponse: EventsResponse): List<CurrentEvent> {
        val eventsFormatted = (eventsResponse.result as ArrayList<*>).map { eventToFormat ->
            (eventToFormat as LinkedTreeMap<*, *>).toCurrentEvent()
        }
        return eventsFormatted
    }
}