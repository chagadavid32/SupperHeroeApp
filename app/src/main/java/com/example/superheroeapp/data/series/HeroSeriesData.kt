package com.example.superheroeapp.data.series

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroSeriesData(

    val id: Int,
    val title: String,
    val thumbnail: HeroSeriesThumbnail,
    val urls: List<Urls>

)

@JsonClass(generateAdapter = true)
data class JsonSeries(

    val data: SeriesData

)

@JsonClass(generateAdapter = true)
data class SeriesData(

    val results: List<HeroSeriesData>

)
