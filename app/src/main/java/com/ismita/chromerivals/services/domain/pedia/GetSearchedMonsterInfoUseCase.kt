package com.ismita.chromerivals.services.domain.pedia

import com.google.gson.internal.LinkedTreeMap
import com.ismita.chromerivals.models.pedia.Monster
import com.ismita.chromerivals.services.repositories.ChromeRivalsRepository
import com.ismita.chromerivals.utils.extensions.AnyExtension.toMonster
import javax.inject.Inject

class GetSearchedMonsterInfoUseCase @Inject constructor(
    private val repository: ChromeRivalsRepository,
) {
    suspend operator fun invoke(id: String): Monster = getMonster(id)

    private suspend fun getMonster(id: String): Monster {
        val monsterResponse = repository.getMonsterById(id = id)
        return  monsterTreeToMonster((monsterResponse.result as LinkedTreeMap<*, *>))
    }

    private fun monsterTreeToMonster(monsterTree: LinkedTreeMap<*, *>): Monster {
        return monsterTree.toMonster()
    }
}