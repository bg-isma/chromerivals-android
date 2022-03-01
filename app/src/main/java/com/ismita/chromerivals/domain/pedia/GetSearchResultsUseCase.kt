package com.ismita.chromerivals.domain.pedia

import com.ismita.chromerivals.data.model.pedia.PediaElement
import com.ismita.chromerivals.data.model.responses.SearchResponse
import com.ismita.chromerivals.data.service.api.repositories.event.ChromeRivalsRepository
import com.ismita.chromerivals.utils.extensions.PediaResultExtension.searchResultToListOfItems
import javax.inject.Inject

class GetSearchResultsUseCase @Inject constructor(
    private val repository: ChromeRivalsRepository
) {
    suspend operator fun invoke(query: String): List<PediaElement> = getSearchedItems(query)

    private suspend fun getSearchedItems(query: String): List<PediaElement> {
        val searchResponse = repository.searchItems(query = query)
        return searchResponseToPediaElementList(searchResponse)
    }

    private fun searchResponseToPediaElementList(searchResponse: SearchResponse): List<PediaElement> {
        return searchResponse.result.searchResultToListOfItems()
    }
}