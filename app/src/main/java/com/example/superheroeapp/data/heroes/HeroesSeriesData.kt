package com.example.superheroeapp.data.heroes

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class HeroesSeriesData(

    var collectionURI: String

): Parcelable
