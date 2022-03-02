package com.ismita.chromerivals.data.service.api.repositories.event

import com.ismita.chromerivals.data.model.responses.EventsResponse

interface CREventRepositoryInterface {
    suspend fun getOutpostsEvents(): EventsResponse
    suspend fun getCurrentEvents(): EventsResponse
    suspend fun getMothershipsEvent(): EventsResponse
}