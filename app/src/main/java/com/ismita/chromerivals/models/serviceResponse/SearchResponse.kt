package com.ismita.chromerivals.models.serviceResponse

import com.ismita.chromerivals.models.pedia.Item
import com.ismita.chromerivals.models.pedia.Monster

data class SearchResponse(val result: SearchResult)

data class SearchResult(val items: List<Item>, val monsters: List<Monster>)