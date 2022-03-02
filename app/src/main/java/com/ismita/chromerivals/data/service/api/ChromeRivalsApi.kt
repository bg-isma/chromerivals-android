package com.ismita.chromerivals.data.service.api

import com.ismita.chromerivals.data.model.responses.EventsResponse
import com.ismita.chromerivals.data.model.responses.PediaResponse
import com.ismita.chromerivals.data.model.responses.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChromeRivalsApi {

    @GET("pedia/search/{query}")
    suspend fun getSearchResults(@Path("query") query: String?): Response<SearchResponse>?

    @GET("pedia/item/{id}")
    suspend fun getItemById(@Path("id") id: String): Response<PediaResponse>

    @GET("pedia/monster/{id}")
    suspend fun getMonsterById(@Path("id") id: String): Response<PediaResponse>

    @GET("info/outposts")
    suspend fun getOutpostsEvents(): Response<EventsResponse>

    @GET("info/server/events")
    suspend fun getCurrentEvents(): Response<EventsResponse>

    @GET("info/motherships")
    suspend fun getMothershipsEvent(): Response<EventsResponse>

}