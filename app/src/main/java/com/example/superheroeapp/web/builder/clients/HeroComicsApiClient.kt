package com.example.superheroeapp.web.builder.clients

import com.example.superheroeapp.data.comics.JsonComic
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface HeroComicsApiClient {

    @GET()
    fun getHeroComicsDataAsync(
        @Url url: String,
        @Query("limit") limit: Int,
        @Query("apikey") apikey: String,
        @Query("ts") ts: Int,
        @Query("hash") hash: String,
    ) : Deferred<Response<JsonComic>>

}