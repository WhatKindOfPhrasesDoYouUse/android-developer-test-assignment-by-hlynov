package com.example.khlynovapp.data.api.response.artist

import com.example.khlynovapp.data.api.response.ApiImage
import com.google.gson.annotations.SerializedName

data class ApiArtistInfo(
    @SerializedName("name")
    val name: String,

    @SerializedName("mbid")
    val mbid: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("image")
    val image: List<ApiImage>,

    @SerializedName("bio")
    val bio: ApiBio?,

    @SerializedName("stats")
    val stats: ApiStats
)