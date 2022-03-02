package com.ismita.chromerivals.data.service.database.repositories.history

import com.ismita.chromerivals.data.model.pedia.HistoryElementDB

interface CRHistoryRepositoryInterface {
    suspend fun getAllHistoryElements(): List<HistoryElementDB>
    suspend fun deleteHistoryElement(item: HistoryElementDB)
    suspend fun deleteAll()
    suspend fun insertHistoryElement(item: HistoryElementDB)
}