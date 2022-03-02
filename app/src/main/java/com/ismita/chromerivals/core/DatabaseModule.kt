package com.ismita.chromerivals.core

import android.content.Context
import androidx.room.Room
import com.ismita.chromerivals.data.service.database.ChromeRivalsDB
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsEventRoom
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsHistoryRoom
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsThemeRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideChromeRivalsDB(@ApplicationContext appContext: Context): ChromeRivalsDB {
        return Room.databaseBuilder(
            appContext,
            ChromeRivalsDB::class.java,
            "RssReader"
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemsDao(chromeRivalsDB: ChromeRivalsDB): ChromeRivalsEventRoom {
        return chromeRivalsDB.eventDao()
    }

    @Provides
    @Singleton
    fun provideEventsDao(chromeRivalsDB: ChromeRivalsDB): ChromeRivalsHistoryRoom {
        return chromeRivalsDB.historyDao()
    }

    @Provides
    @Singleton
    fun provideThemeDao(chromeRivalsDB: ChromeRivalsDB): ChromeRivalsThemeRoom {
        return chromeRivalsDB.themeDao()
    }

}

