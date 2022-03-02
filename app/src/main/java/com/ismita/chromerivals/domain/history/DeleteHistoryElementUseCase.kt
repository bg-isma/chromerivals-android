package com.ismita.chromerivals.domain.history

import com.ismita.chromerivals.data.model.pedia.HistoryElementDB
import com.ismita.chromerivals.data.service.database.repositories.history.CRHistoryRepositoryInterface
import javax.inject.Inject

class DeleteHistoryElementUseCase @Inject constructor(
    private val historyRepository: CRHistoryRepositoryInterface
) {
    suspend operator fun invoke(item: HistoryElementDB): Unit = historyRepository.deleteHistoryElement(item = item)
}