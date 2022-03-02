package com.ismita.chromerivals.data.service.api.repositories.pedia

import com.ismita.chromerivals.data.model.responses.PediaResponse
import com.ismita.chromerivals.data.model.responses.SearchResponse
import com.ismita.chromerivals.data.service.api.ChromeRivalsService
import javax.inject.Inject

class CRPediaRepository @Inject constructor(
    private val service: ChromeRivalsService
): CRPediaRepositoryInterface {

    override suspend fun searchItems(query: String): SearchResponse {
        return service.getAllSearchedItems(query = query)
    }

    override suspend fun getItemById(id : String): PediaResponse {
        return service.getItemById(id = id)
    }

    override suspend fun getMonsterById(id : String): PediaResponse {
        return service.getMonsterById(id = id)
    }

}