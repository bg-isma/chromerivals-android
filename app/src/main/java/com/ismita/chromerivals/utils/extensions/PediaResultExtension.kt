package com.ismita.chromerivals.utils.extensions

import com.ismita.chromerivals.data.model.pedia.PediaElement
import com.ismita.chromerivals.data.model.responses.SearchResult
import com.ismita.chromerivals.utils.Utils

object PediaResultExtension {

    fun SearchResult.searchResultToListOfItems(): List<PediaElement> {
        return Utils.concatenate(this.items, this.monsters)
    }

}