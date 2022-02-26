package com.example.superheroeapp.web.builder.clients

import com.example.superheroeapp.constants.WebConstants
import com.example.superheroeapp.data.comics.HeroesComicData
import com.example.superheroeapp.data.comics.JsonComic
import com.example.superheroeapp.data.heroes.Json
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface HeroesApiClient {

    @GET(WebConstants.CHARACTERS_END_POINT)
    fun getHeroesDataAsync(
        @Query("limit") limit: Int,
        @Query("apikey") apikey : String,
        @Query("ts") ts : Int,
        @Query("hash") hash : String,
    ) : Deferred<Response<Json>>

}