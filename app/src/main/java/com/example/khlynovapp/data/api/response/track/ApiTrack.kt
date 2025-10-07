package com.example.khlynovapp.data.api.response.track

import com.example.khlynovapp.data.api.response.ApiImage
import com.google.gson.annotations.SerializedName

data class ApiTrack(
    @SerializedName("name")
    val name: String,

    @SerializedName("playcount")
    val playcount: String?,

    @SerializedName("listeners")
    val listeners: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("image")
    val image: List<ApiImage>,

    @SerializedName("artist")
    val artist: ApiTrackArtist?,

    @SerializedName("@attr")
    val attr: ApiTrackAttr?
)
