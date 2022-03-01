package com.ismita.chromerivals.services.domain.pedia

import com.google.gson.internal.LinkedTreeMap
import com.ismita.chromerivals.models.pedia.Item
import com.ismita.chromerivals.services.repositories.ChromeRivalsRepository
import com.ismita.chromerivals.utils.extensions.AnyExtension.toItem
import javax.inject.Inject

class GetSearchedItemInfoUseCase @Inject constructor(
    private val repository: ChromeRivalsRepository,
) {
    suspend operator fun invoke(id: String): Item = getItem(id)

    private suspend fun getItem(id: String): Item {
        val itemResponse = repository.getItemById(id = id)
        return itemTreeToItem(itemResponse.result as LinkedTreeMap<*, *>)
    }

    private fun itemTreeToItem(itemTree: LinkedTreeMap<*, *>): Item {
        return itemTree.toItem()
    }


}