package com.example.superheroeapp.data.events

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroEventsData(

    val id: Int,
    val title: String,
    val thumbnail: HeroesEventsThumbnail,
    val urls: List<HeroEventsUrls>

)

@JsonClass(generateAdapter = true)
data class JsonEvents(

    val data: EventsData

)

@JsonClass(generateAdapter = true)
data class EventsData(

    val results : List<HeroEventsData>

)
