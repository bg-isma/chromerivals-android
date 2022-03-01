package com.ismita.chromerivals.services.database

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.ismita.chromerivals.models.ThemeDB;

@Dao
interface ChromeRivalsThemeRoom {

    @Query("SELECT * from theme_table LIMIT 1")
    fun getTheme(): ThemeDB?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: ThemeDB): Long

    @Update
    fun update(item: ThemeDB)

}