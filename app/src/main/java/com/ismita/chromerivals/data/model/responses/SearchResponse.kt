package com.ismita.chromerivals.data.model.responses

import com.ismita.chromerivals.data.model.pedia.Item
import com.ismita.chromerivals.data.model.pedia.Monster

data class SearchResponse(val result: SearchResult? = null)

data class SearchResult(val items: List<Item>, val monsters: List<Monster>)