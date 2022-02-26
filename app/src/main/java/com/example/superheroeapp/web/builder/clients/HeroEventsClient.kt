package com.example.superheroeapp.web.builder.clients

import com.example.superheroeapp.constants.WebConstants
import com.example.superheroeapp.data.events.JsonEvents
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface HeroEventsClient {

    @GET()
    fun getHeroesDataAsync(
        @Url url: String,
        @Query("limit") limit: Int,
        @Query("apikey") apikey : String,
        @Query("ts") ts : Int,
        @Query("hash") hash : String,
    ) : Deferred<Response<JsonEvents>>

}