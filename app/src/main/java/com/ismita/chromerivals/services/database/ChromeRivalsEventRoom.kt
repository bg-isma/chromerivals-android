package com.ismita.chromerivals.services.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ismita.chromerivals.models.event.EventDB

@Dao
interface ChromeRivalsEventRoom {

    @Query("SELECT * from event_table where bcu != null")
    suspend fun getAllMotherships(): List<EventDB>

    @Query("SELECT * from event_table where outpostName != null")
    suspend fun getAllOutposts(): List<EventDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvents(events: List<EventDB>)

    @Query("DELETE FROM event_table where bcu != null")
    fun deleteAllMotherships()

    @Query("DELETE FROM event_table where outpostName != null")
    fun deleteAllOutpost()

}