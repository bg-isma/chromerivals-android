package com.ismita.chromerivals.data.service.api.repositories.event

import com.ismita.chromerivals.data.model.responses.SearchResponse
import com.ismita.chromerivals.data.model.responses.EventsResponse
import com.ismita.chromerivals.data.model.responses.PediaResponse
import com.ismita.chromerivals.data.service.api.ChromeRivalsService
import javax.inject.Inject

class ChromeRivalsRepository @Inject constructor(
    private val service: ChromeRivalsService
) {

    suspend fun searchItems(query: String): SearchResponse {
        return service.getAllSearchedItems(query = query)
    }

    suspend fun getItemById(id : String): PediaResponse {
        return service.getItemById(id = id)
    }

    suspend fun getMonsterById(id : String): PediaResponse {
        return service.getMonsterById(id = id)
    }

    suspend fun getOutpostsEvents(): EventsResponse {
        return service.getOutpostsEvents()
    }

    suspend fun getCurrentEvents(): EventsResponse {
        return service.getCurrentEvents()
    }

    suspend fun getMothershipsEvent(): EventsResponse {
        return service.getMothershipsEvent()
    }

}