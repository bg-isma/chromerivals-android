package com.ismita.chromerivals.domain.history

import com.ismita.chromerivals.data.service.database.repositories.history.CRHistoryRepositoryInterface
import javax.inject.Inject

class DeleteAllHistoryElementsUseCase @Inject constructor(
    private val historyRepository: CRHistoryRepositoryInterface
) {
    suspend operator fun invoke(): Unit = historyRepository.deleteAll()
}