package com.example.superheroeapp.data.heroes

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class HeroesThumbnailData(

    var path : String,
    var extension: String
): Parcelable
