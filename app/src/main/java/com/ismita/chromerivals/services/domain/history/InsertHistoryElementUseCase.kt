package com.ismita.chromerivals.services.domain.history

import com.ismita.chromerivals.models.pedia.HistoryElementDB
import com.ismita.chromerivals.services.repositories.HistoryElementsRepository
import javax.inject.Inject

class InsertHistoryElementUseCase @Inject constructor(
    private val repository: HistoryElementsRepository
) {
    suspend operator fun invoke(query: String): Unit = repository.insertHistoryElement(
        HistoryElementDB(query = query)
    )
}