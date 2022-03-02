package com.ismita.chromerivals.data.service.database.repositories.history

import com.ismita.chromerivals.data.model.pedia.HistoryElementDB
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsHistoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CRHistoryRepository @Inject constructor(
    private val historyRoom: ChromeRivalsHistoryRoom
): CRHistoryRepositoryInterface {

    override suspend fun getAllHistoryElements(): List<HistoryElementDB> {
        return withContext(Dispatchers.IO) {
            historyRoom.getAll()
        }
    }

    override suspend fun deleteHistoryElement(item: HistoryElementDB) {
        withContext(Dispatchers.IO) {
            historyRoom.delete(item)
        }
    }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            historyRoom.deleteAll()
        }
    }

    override suspend fun insertHistoryElement(item: HistoryElementDB) {
        withContext(Dispatchers.IO) {
            historyRoom.insert(item = item)
        }
    }

}