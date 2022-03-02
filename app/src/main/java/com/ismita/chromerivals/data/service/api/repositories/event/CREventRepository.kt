package com.ismita.chromerivals.data.service.api.repositories.event

import com.ismita.chromerivals.data.model.responses.EventsResponse
import com.ismita.chromerivals.data.service.api.ChromeRivalsService
import javax.inject.Inject

class CREventRepository @Inject constructor(
    private val service: ChromeRivalsService
): CREventRepositoryInterface {

    override suspend fun getOutpostsEvents(): EventsResponse {
        return service.getOutpostsEvents()
    }

    override suspend fun getCurrentEvents(): EventsResponse {
        return service.getCurrentEvents()
    }

    override suspend fun getMothershipsEvent(): EventsResponse {
        return service.getMothershipsEvent()
    }

}