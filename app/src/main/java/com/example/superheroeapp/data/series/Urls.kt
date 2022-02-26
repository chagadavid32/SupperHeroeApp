package com.example.superheroeapp.data.series

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Urls(

    val type: String,
    val url: String

)
