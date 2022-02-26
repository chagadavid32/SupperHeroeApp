package com.example.superheroeapp.data.heroes

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class HeroesData(

    var id : Long,
    var name: String,
    var thumbnail: HeroesThumbnailData,
    var comics : HeroesRootComicData,
    var series: HeroesSeriesData,
    var events: HeroesEventsData


) : Parcelable

@JsonClass(generateAdapter = true)
data class Json(

    var data: Data
)

@JsonClass(generateAdapter = true)
data class Data(

    var results: List<HeroesData>

)
