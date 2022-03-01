package com.ismita.chromerivals.services

import com.ismita.chromerivals.BuildConfig
import com.ismita.chromerivals.models.serviceResponse.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ChromeRivalsApi {

    @Headers(
        "Accept: */*",
        "Cr-Api-Key: ${BuildConfig.CR_PUBLIC_API_KEY}"
    )
    @GET("pedia/search/{query}")
    suspend fun getSearchResults(@Path("query") query: String?): Response<SearchResponse>?

    @Headers(
        "Accept: */*",
        "Cr-Api-Key: ${BuildConfig.CR_PUBLIC_API_KEY}"
    )
    @GET("pedia/item/{id}")
    suspend fun getItemById(@Path("id") id: String): Response<PediaResponse>

    @Headers(
        "Accept: */*",
        "Cr-Api-Key: ${BuildConfig.CR_PUBLIC_API_KEY}"
    )
    @GET("pedia/monster/{id}")
    suspend fun getMonsterById(@Path("id") id: String): Response<PediaResponse>

    @Headers(
        "Accept: */*",
        "Cr-Api-Key: ${BuildConfig.CR_PUBLIC_API_KEY}"
    )
    @GET("info/outposts")
    suspend fun getOutpostsEvents(): Response<EventsResponse>

    @Headers(
        "Accept: */*",
        "Cr-Api-Key: ${BuildConfig.CR_PUBLIC_API_KEY}"
    )
    @GET("info/server/events")
    suspend fun getCurrentEvents(): Response<EventsResponse>

    @Headers(
        "Accept: */*",
        "Cr-Api-Key: ${BuildConfig.CR_PUBLIC_API_KEY}"
    )
    @GET("info/motherships")
    suspend fun getMothershipsEvent(): Response<EventsResponse>

}