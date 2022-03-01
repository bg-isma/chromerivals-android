package com.ismita.chromerivals.services.database

import androidx.room.*
import com.ismita.chromerivals.models.pedia.HistoryElementDB

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