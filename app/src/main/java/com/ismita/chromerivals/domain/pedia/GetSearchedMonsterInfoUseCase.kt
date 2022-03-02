package com.ismita.chromerivals.domain.pedia

import com.google.gson.internal.LinkedTreeMap
import com.ismita.chromerivals.data.model.pedia.Monster
import com.ismita.chromerivals.data.service.api.repositories.pedia.CRPediaRepositoryInterface
import com.ismita.chromerivals.utils.extensions.AnyExtension.toMonster
import javax.inject.Inject

class GetSearchedMonsterInfoUseCase @Inject constructor(
    private val pediaRepository: CRPediaRepositoryInterface,
) {
    suspend operator fun invoke(id: String): Monster = getMonster(id)

    private suspend fun getMonster(id: String): Monster {
        val monsterResponse = pediaRepository.getMonsterById(id = id)
        return  monsterTreeToMonster((monsterResponse.result as LinkedTreeMap<*, *>))
    }

    private fun monsterTreeToMonster(monsterTree: LinkedTreeMap<*, *>): Monster {
        return monsterTree.toMonster()
    }
}