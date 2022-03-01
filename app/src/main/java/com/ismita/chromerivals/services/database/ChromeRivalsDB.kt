package com.ismita.chromerivals.services.database

import androidx.room.Database;
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ismita.chromerivals.models.event.EventDB
import com.ismita.chromerivals.models.ThemeDB
import com.ismita.chromerivals.models.pedia.HistoryElementDB
import com.ismita.chromerivals.services.database.converters.Converters

@Database(entities = [HistoryElementDB::class, ThemeDB::class, EventDB::class], version = 1)
@TypeConverters(Converters::class)
abstract class ChromeRivalsDB : RoomDatabase() {
    abstract fun eventDao(): ChromeRivalsEventRoom
    abstract fun historyDao(): ChromeRivalsHistoryRoom
    abstract fun themeDao(): ChromeRivalsThemeRoom
}