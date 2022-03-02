package com.ismita.chromerivals.services.domain.events

import com.google.gson.internal.LinkedTreeMap
import com.ismita.chromerivals.domain.events.GetMothershipsEventsUseCase
import com.ismita.chromerivals.data.model.event.UpcomingEvent
import com.ismita.chromerivals.data.model.responses.EventsResponse
import com.ismita.chromerivals.data.service.api.repositories.event.CREventRepositoryInterface
import com.ismita.chromerivals.data.service.database.repositories.event.CREventRoomRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetMothershipsEventsUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: CREventRepositoryInterface
    @RelaxedMockK
    private lateinit var eventRepository: CREventRoomRepository

    lateinit var getMothershipsEventsUseCase : GetMothershipsEventsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMothershipsEventsUseCase = GetMothershipsEventsUseCase(repository, eventRepository)
    }

    @Test
    fun `when api doesn't return nothing then get elements from database` () = runBlocking {
        // GIVEN
        val databaseMotherships = listOf(UpcomingEvent(ani = "18-13-2020"), UpcomingEvent(bcu = "18-13-2020"))
        coEvery { repository.getMothershipsEvent() } returns EventsResponse(result = null)
        coEvery { eventRepository.getAllMotherships() } returns databaseMotherships

        // WHEN
        val response = getMothershipsEventsUseCase()

        // THEN
        coVerify { eventRepository.getAllMotherships() }
        assert(response == databaseMotherships)
    }

    @Test
    fun `when api return something database values change and response is an EventResponse result formatted to an UpcomingEvent list` () = runBlocking {
        // GIVEN
        val newMotherships = LinkedTreeMap<String, String>()
        newMotherships["bcu"] = "18-13-2020"
        newMotherships["ani"] = "18-13-2020"
        val newMothershipsFormatted = listOf(UpcomingEvent(ani = "18-13-2020"), UpcomingEvent(bcu = "18-13-2020"))

        coEvery { repository.getMothershipsEvent() } returns EventsResponse(result = newMotherships)

        // WHEN
        val response = getMothershipsEventsUseCase()

        // THEN
        coVerify(exactly = 1) { eventRepository.deleteAllMotherships() }
        coVerify(exactly = 1) { eventRepository.insertEvents(any()) }
        coVerify(exactly = 0) { eventRepository.getAllMotherships() }

        assert(response == newMothershipsFormatted)
    }

    @After
    fun tearDown() {}

}