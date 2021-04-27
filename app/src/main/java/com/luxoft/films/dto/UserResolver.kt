package com.luxoft.films.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResolver(
    @SerializedName("filename") val filename: String,
    @SerializedName("type") val type: String,
    @SerializedName("language") val language: String,
    @SerializedName("raw_url") val raw_url: String,
    @SerializedName("size") val size: Int
) : Parcelable