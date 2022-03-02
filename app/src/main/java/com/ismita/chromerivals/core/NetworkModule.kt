package com.ismita.chromerivals.core

import com.ismita.chromerivals.data.service.api.ChromeRivalsApi
import com.ismita.chromerivals.data.service.api.repositories.event.CREventRepository
import com.ismita.chromerivals.data.service.database.repositories.event.CREventRoomRepositoryInterface
import com.ismita.chromerivals.utils.network.RetrofitInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(RetrofitInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.chromerivals.net/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideChromeRivalsApi(retrofit: Retrofit) : ChromeRivalsApi {
        return retrofit.create(ChromeRivalsApi::class.java)
    }

}