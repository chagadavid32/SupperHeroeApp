package com.example.superheroeapp.web.builder

import com.example.superheroeapp.web.builder.clients.HeroComicsApiClient
import com.example.superheroeapp.web.builder.clients.HeroEventsClient
import com.example.superheroeapp.web.builder.clients.HeroSeriesClient
import com.example.superheroeapp.web.builder.clients.HeroesApiClient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance private constructor(
    var baseUrl: String
){

    fun createHeroesApiService(): HeroesApiClient {
        val retrofitService: HeroesApiClient by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("$baseUrl/")
                .build()

            return@lazy retrofit.create(HeroesApiClient::class.java)
        }
        return retrofitService
    }

    fun createHeroComicsApiService(): HeroComicsApiClient {
        val retrofitService: HeroComicsApiClient by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("$baseUrl/")
                .build()

            return@lazy retrofit.create(HeroComicsApiClient::class.java)
        }
        return retrofitService
    }

    fun createHeroSeriessApiService(): HeroSeriesClient {
        val retrofitService: HeroSeriesClient by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("$baseUrl/")
                .build()

            return@lazy retrofit.create(HeroSeriesClient::class.java)
        }
        return retrofitService
    }

    fun createHeroEventsApiService(): HeroEventsClient {
        val retrofitService: HeroEventsClient by lazy {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("$baseUrl/")
                .build()

            return@lazy retrofit.create(HeroEventsClient::class.java)
        }
        return retrofitService
    }

    data class Builder(
        private var url: String? = null
    ){

        fun url(url: String) = apply { this.url = url }

        fun buildBase() = RetrofitInstance(url!!).createHeroesApiService()
        fun buildComics() = RetrofitInstance(url!!).createHeroComicsApiService()
        fun buildSereies() = RetrofitInstance(url!!).createHeroSeriessApiService()
        fun buildEvents() = RetrofitInstance(url!!).createHeroEventsApiService()

    }

}