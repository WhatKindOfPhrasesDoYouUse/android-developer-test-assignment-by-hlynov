package com.example.khlynovapp.data.api.response.track

import com.google.gson.annotations.SerializedName

data class ApiTopTracks(
    @SerializedName("track")
    val track: List<ApiTrack>,

    @SerializedName("@attr")
    val attr: ApiTopTrackAttr
)
