package com.ismita.chromerivals.data.service.database

import androidx.room.*
import com.ismita.chromerivals.data.model.pedia.HistoryElementDB

@Dao
interface ChromeRivalsHistoryRoom {

    @Query("SELECT * from history_table")
    suspend fun getAll(): List<HistoryElementDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: HistoryElementDB) : Long

    @Delete
    fun delete(item: HistoryElementDB)

    @Query("DELETE FROM history_table")
    fun deleteAll()

}