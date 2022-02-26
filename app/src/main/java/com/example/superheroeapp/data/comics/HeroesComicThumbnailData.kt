package com.example.superheroeapp.data.comics

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroesComicThumbnailData(

    val path: String,
    val extension: String

)
