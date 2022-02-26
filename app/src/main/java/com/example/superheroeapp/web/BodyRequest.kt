package com.example.superheroeapp.web

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BodyRequest(

    var apikey: String,
    var ts: Int = 1,
    var hash: String

)
