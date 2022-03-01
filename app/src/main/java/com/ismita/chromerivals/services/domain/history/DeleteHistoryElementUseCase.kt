package com.ismita.chromerivals.services.domain.history

import com.ismita.chromerivals.models.pedia.HistoryElementDB
import com.ismita.chromerivals.services.repositories.HistoryElementsRepository
import javax.inject.Inject

class DeleteHistoryElementUseCase @Inject constructor(
    private val repository: HistoryElementsRepository
) {
    suspend operator fun invoke(item: HistoryElementDB): Unit = repository.deleteHistoryElement(item = item)
}