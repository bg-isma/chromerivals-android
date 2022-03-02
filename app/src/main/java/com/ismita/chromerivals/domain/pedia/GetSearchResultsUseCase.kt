package com.ismita.chromerivals.domain.pedia

import com.ismita.chromerivals.data.model.pedia.PediaElement
import com.ismita.chromerivals.data.model.responses.SearchResponse
import com.ismita.chromerivals.data.service.api.repositories.pedia.CRPediaRepositoryInterface
import com.ismita.chromerivals.utils.extensions.PediaResultExtension.searchResultToListOfItems
import javax.inject.Inject

class GetSearchResultsUseCase @Inject constructor(
    private val pediaRepository: CRPediaRepositoryInterface
) {
    suspend operator fun invoke(query: String): List<PediaElement> = getSearchedItems(query)

    private suspend fun getSearchedItems(query: String): List<PediaElement> {
        val searchResponse = pediaRepository.searchItems(query = query)
        return searchResponseToPediaElementList(searchResponse)
    }

    private fun searchResponseToPediaElementList(searchResponse: SearchResponse): List<PediaElement> {
        return if (searchResponse.result != null) {
            searchResponse.result.searchResultToListOfItems()
        } else emptyList()
    }
}