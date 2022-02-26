package com.example.superheroeapp.data.events

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroesEventsThumbnail(

    val path: String,
    val extension: String

)
