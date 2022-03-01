package com.ismita.chromerivals.utils.extensions

import com.ismita.chromerivals.models.pedia.PediaElement
import com.ismita.chromerivals.models.serviceResponse.SearchResult
import com.ismita.chromerivals.utils.Utils

object PediaResultExtension {

    fun SearchResult.searchResultToListOfItems(): List<PediaElement> {
        return Utils.concatenate(this.items, this.monsters)
    }

}