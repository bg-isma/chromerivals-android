package com.ismita.chromerivals.core

import com.ismita.chromerivals.data.service.api.ChromeRivalsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.chromerivals.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideChromeRivalsApi(retrofit: Retrofit) : ChromeRivalsApi {
        return retrofit.create(ChromeRivalsApi::class.java)
    }

}