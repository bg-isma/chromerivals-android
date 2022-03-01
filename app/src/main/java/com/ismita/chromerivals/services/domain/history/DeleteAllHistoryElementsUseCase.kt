package com.ismita.chromerivals.services.domain.history

import com.ismita.chromerivals.services.repositories.HistoryElementsRepository
import javax.inject.Inject

class DeleteAllHistoryElementsUseCase @Inject constructor(
    private val repository: HistoryElementsRepository
) {
    suspend operator fun invoke(): Unit = repository.deleteAll()
}