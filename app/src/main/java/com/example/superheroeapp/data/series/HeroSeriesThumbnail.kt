package com.example.superheroeapp.data.series

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroSeriesThumbnail(

    val path: String,
    val extension: String

)
