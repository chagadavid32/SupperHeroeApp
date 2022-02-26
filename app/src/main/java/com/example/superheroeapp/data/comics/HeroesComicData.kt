package com.example.superheroeapp.data.comics

import androidx.annotation.Nullable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroesComicData (

    val id: Long,
    val title: String,
    //val description: String = "n",
    val thumbnail: HeroesComicThumbnailData,
    val urls: List<HeroesComicUrlsData>

)

@JsonClass (generateAdapter = true)
data class JsonComic(
    val data: ComicData
)

@JsonClass(generateAdapter = true)
data class ComicData(

    val results: List<HeroesComicData>
)