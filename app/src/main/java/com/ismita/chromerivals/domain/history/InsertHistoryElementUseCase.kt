package com.ismita.chromerivals.domain.history

import com.ismita.chromerivals.data.model.pedia.HistoryElementDB
import com.ismita.chromerivals.data.service.database.repositories.history.CRHistoryRepositoryInterface
import javax.inject.Inject

class InsertHistoryElementUseCase @Inject constructor(
    private val historyRepository: CRHistoryRepositoryInterface
) {
    suspend operator fun invoke(query: String): Unit = historyRepository.insertHistoryElement(
        HistoryElementDB(query = query)
    )
}