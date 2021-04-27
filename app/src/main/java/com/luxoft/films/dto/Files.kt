package com.luxoft.films.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Files (
    @SerializedName("UserResolver.ts") val userResolver : UserResolver
) : Parcelable