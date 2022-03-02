package com.ismita.chromerivals.data.service.database

import androidx.room.Database;
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ismita.chromerivals.data.model.event.EventDB
import com.ismita.chromerivals.data.model.theme.ThemeDB
import com.ismita.chromerivals.data.model.pedia.HistoryElementDB
import com.ismita.chromerivals.data.service.database.converters.Converters
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsEventRoom
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsHistoryRoom
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsThemeRoom

@Database(entities = [HistoryElementDB::class, ThemeDB::class, EventDB::class], version = 1)
@TypeConverters(Converters::class)
abstract class ChromeRivalsDB : RoomDatabase() {
    abstract fun eventDao(): ChromeRivalsEventRoom
    abstract fun historyDao(): ChromeRivalsHistoryRoom
    abstract fun themeDao(): ChromeRivalsThemeRoom
}