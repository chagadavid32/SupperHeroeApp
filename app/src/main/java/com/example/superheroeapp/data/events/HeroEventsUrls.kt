package com.example.superheroeapp.data.events

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroEventsUrls(

    val type: String,
    val url: String

)
