package com.ismita.chromerivals.core

import com.ismita.chromerivals.data.service.api.repositories.event.CREventRepository
import com.ismita.chromerivals.data.service.api.repositories.event.CREventRepositoryInterface
import com.ismita.chromerivals.data.service.api.repositories.pedia.CRPediaRepository
import com.ismita.chromerivals.data.service.api.repositories.pedia.CRPediaRepositoryInterface
import com.ismita.chromerivals.data.service.database.repositories.event.CREventRoomRepository
import com.ismita.chromerivals.data.service.database.repositories.event.CREventRoomRepositoryInterface
import com.ismita.chromerivals.data.service.database.repositories.history.CRHistoryRepository
import com.ismita.chromerivals.data.service.database.repositories.history.CRHistoryRepositoryInterface
import com.ismita.chromerivals.data.service.database.repositories.theme.CRThemeRepository
import com.ismita.chromerivals.data.service.database.repositories.theme.CRThemeRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventRoomRepository(eventRoomRepository: CREventRoomRepository): CREventRoomRepositoryInterface {
        return eventRoomRepository
    }

    @Provides
    @Singleton
    fun provideEventRepository(eventRepository: CREventRepository): CREventRepositoryInterface {
        return eventRepository
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(historyRepository: CRHistoryRepository): CRHistoryRepositoryInterface {
        return historyRepository
    }

    @Provides
    @Singleton
    fun provideThemeRepository(themeRepository: CRThemeRepository): CRThemeRepositoryInterface {
        return themeRepository
    }

    @Provides
    @Singleton
    fun providePediaRepository(pediaRepository: CRPediaRepository): CRPediaRepositoryInterface {
        return pediaRepository
    }

}