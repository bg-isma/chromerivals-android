package com.ismita.chromerivals.services.repositories

import com.ismita.chromerivals.models.serviceResponse.SearchResponse
import com.ismita.chromerivals.models.serviceResponse.EventsResponse
import com.ismita.chromerivals.models.serviceResponse.PediaResponse
import com.ismita.chromerivals.services.ChromeRivalsService
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