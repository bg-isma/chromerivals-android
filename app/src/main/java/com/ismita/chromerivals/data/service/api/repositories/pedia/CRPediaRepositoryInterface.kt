package com.ismita.chromerivals.data.service.api.repositories.pedia

import com.ismita.chromerivals.data.model.responses.PediaResponse
import com.ismita.chromerivals.data.model.responses.SearchResponse

interface CRPediaRepositoryInterface {
    suspend fun searchItems(query: String): SearchResponse
    suspend fun getItemById(id : String): PediaResponse
    suspend fun getMonsterById(id : String): PediaResponse
}