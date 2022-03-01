package com.ismita.chromerivals.services.repositories

import com.ismita.chromerivals.models.pedia.HistoryElementDB
import com.ismita.chromerivals.services.database.ChromeRivalsHistoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistoryElementsRepository @Inject constructor(
    private val historyRoom: ChromeRivalsHistoryRoom
) {

    suspend fun getAllHistoryElements(): List<HistoryElementDB> {
        return withContext(Dispatchers.IO) {
            historyRoom.getAll()
        }
    }

    suspend fun deleteHistoryElement(item: HistoryElementDB) {
        withContext(Dispatchers.IO) {
            historyRoom.delete(item)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            historyRoom.deleteAll()
        }
    }

    suspend fun insertHistoryElement(item: HistoryElementDB) {
        withContext(Dispatchers.IO) {
            historyRoom.insert(item = item)
        }
    }

}