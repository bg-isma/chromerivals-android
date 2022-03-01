package com.ismita.chromerivals.domain.history

import com.ismita.chromerivals.data.service.database.repositories.history.HistoryElementsRepository
import javax.inject.Inject

class DeleteAllHistoryElementsUseCase @Inject constructor(
    private val repository: HistoryElementsRepository
) {
    suspend operator fun invoke(): Unit = repository.deleteAll()
}