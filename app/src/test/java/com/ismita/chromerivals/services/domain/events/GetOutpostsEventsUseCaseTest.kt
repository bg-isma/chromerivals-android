package com.ismita.chromerivals.services.domain.events

import com.ismita.chromerivals.models.event.UpcomingEvent
import com.ismita.chromerivals.models.serviceResponse.EventsResponse
import com.ismita.chromerivals.services.repositories.ChromeRivalsEventRepository
import com.ismita.chromerivals.services.repositories.ChromeRivalsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test

class GetOutpostsEventsUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: ChromeRivalsRepository
    @RelaxedMockK
    private lateinit var eventRepository: ChromeRivalsEventRepository

    lateinit var getOutpostsEventsUseCase : GetOutpostsEventsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getOutpostsEventsUseCase = GetOutpostsEventsUseCase(repository, eventRepository)
    }

    @Test
    fun `when api doesn't return nothing then get elements from database` () = runBlocking {
        // GIVEN
        val databaseOutposts = listOf(
            UpcomingEvent(deployTime = "18-13-2020", outpostName = "", influence = 1.0),
            UpcomingEvent(deployTime = "18-13-2020", outpostName = "", influence = 1.0),
            UpcomingEvent(deployTime = "18-13-2020", outpostName = "", influence = 1.0),
            UpcomingEvent(deployTime = "18-13-2020", outpostName = "", influence = 1.0)
        )

        coEvery { repository.getOutpostsEvents() } returns EventsResponse(result = null)
        coEvery { eventRepository.getAllOutposts() } returns databaseOutposts

        // WHEN
        val response = getOutpostsEventsUseCase()

        // THEN
        coVerify { eventRepository.getAllOutposts() }
        assert(response == databaseOutposts)
    }

    @Test
    fun `when api return something database values change and response is an EventResponse result formatted to an UpcomingEvent list` () = runBlocking {
        // GIVEN
        val outpostTree = linkedMapOf("deployTime" to "18-13-2020", "outpostName" to "", "influence" to "1.0")
        val newOutposts = arrayOf(outpostTree, outpostTree, outpostTree)
        val newOutpostsFormatted = listOf(
            UpcomingEvent(deployTime = "18-13-2020", outpostName = "", influence = 1.0),
            UpcomingEvent(deployTime = "18-13-2020", outpostName = "", influence = 1.0),
            UpcomingEvent(deployTime = "18-13-2020", outpostName = "", influence = 1.0),
        )
        coEvery { repository.getOutpostsEvents() } returns EventsResponse(result = newOutposts)

        // WHEN
        val response = getOutpostsEventsUseCase()

        // THEN
        coVerify(exactly = 1) { eventRepository.deleteAllOutposts() }
        coVerify(exactly = 1) { eventRepository.insertEvents(any()) }
        coVerify(exactly = 0) { eventRepository.getAllOutposts() }

        assert(response == newOutpostsFormatted)
    }

    @After
    fun tearDown() {}
}