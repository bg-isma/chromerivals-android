package com.ismita.chromerivals.services

import com.ismita.chromerivals.models.pedia.Item
import com.ismita.chromerivals.models.pedia.Monster
import com.ismita.chromerivals.models.serviceResponse.SearchResponse
import com.ismita.chromerivals.models.serviceResponse.SearchResult
import com.ismita.chromerivals.models.serviceResponse.EventsResponse
import com.ismita.chromerivals.models.serviceResponse.PediaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChromeRivalsService @Inject constructor(
    private val api: ChromeRivalsApi,
) {

    suspend fun getAllSearchedItems(query: String): SearchResponse {
        return withContext(Dispatchers.IO) {
            try {
                val results = api.getSearchResults(query)?.body()
                results as SearchResponse
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong  ${e.message}")
                SearchResponse(result = SearchResult(emptyList(), emptyList()))
            }
        }
    }

    suspend fun getItemById(id: String): PediaResponse {
        return withContext(Dispatchers.IO) {
            try {
                val results = api.getItemById(id).body()
                results as PediaResponse
            } catch(e: Throwable) {
                println("Ooops: Something else went wrong  ${e.message}")
                PediaResponse(Item())
            }
        }
    }

    suspend fun getMonsterById(id: String): PediaResponse {
        return withContext(Dispatchers.IO) {
            try {
                val results = api.getMonsterById(id).body()
                results as PediaResponse
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong  ${e.message}")
                PediaResponse(Monster())
            }
        }
    }

    suspend fun getOutpostsEvents(): EventsResponse {
        return withContext(Dispatchers.IO) {
            try {
                val results = api.getOutpostsEvents().body()
                results as EventsResponse
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong  ${e.message}")
                EventsResponse()
            }
        }
    }

    suspend fun getCurrentEvents(): EventsResponse {
        return withContext(Dispatchers.IO) {
            try {
                val results = api.getCurrentEvents().body()
                results as EventsResponse
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong  ${e.message}")
                EventsResponse()
            }
        }
    }

    suspend fun getMothershipsEvent(): EventsResponse {
        return withContext(Dispatchers.IO) {
            try {
                val results = api.getMothershipsEvent().body()
                results as EventsResponse
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong  ${e.message}")
                EventsResponse()
            }
        }
    }

}