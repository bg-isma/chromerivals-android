package com.ismita.chromerivals.domain.history

import com.ismita.chromerivals.data.model.pedia.HistoryElementDB
import com.ismita.chromerivals.data.service.database.repositories.history.HistoryElementsRepository
import javax.inject.Inject

class InsertHistoryElementUseCase @Inject constructor(
    private val repository: HistoryElementsRepository
) {
    suspend operator fun invoke(query: String): Unit = repository.insertHistoryElement(
        HistoryElementDB(query = query)
    )
}