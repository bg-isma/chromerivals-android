package com.ismita.chromerivals.domain.pedia

import com.google.gson.internal.LinkedTreeMap
import com.ismita.chromerivals.data.model.pedia.Item
import com.ismita.chromerivals.data.service.api.repositories.pedia.CRPediaRepositoryInterface
import com.ismita.chromerivals.utils.extensions.AnyExtension.toItem
import javax.inject.Inject

class GetSearchedItemInfoUseCase @Inject constructor(
    private val pediaRepository: CRPediaRepositoryInterface,
) {
    suspend operator fun invoke(id: String): Item = getItem(id)

    private suspend fun getItem(id: String): Item {
        val itemResponse = pediaRepository.getItemById(id = id)
        return itemTreeToItem(itemResponse.result as LinkedTreeMap<*, *>)
    }

    private fun itemTreeToItem(itemTree: LinkedTreeMap<*, *>): Item {
        return itemTree.toItem()
    }


}