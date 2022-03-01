package com.ismita.chromerivals.domain.history

import com.ismita.chromerivals.data.model.pedia.HistoryElementDB
import com.ismita.chromerivals.data.service.database.repositories.history.HistoryElementsRepository
import javax.inject.Inject

class DeleteHistoryElementUseCase @Inject constructor(
    private val repository: HistoryElementsRepository
) {
    suspend operator fun invoke(item: HistoryElementDB): Unit = repository.deleteHistoryElement(item = item)
}