package com.example.superheroeapp.data.comics

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroesComicUrlsData(

    val type: String,
    val url: String

)
