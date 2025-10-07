package com.example.khlynovapp.data.api.response.track

import com.google.gson.annotations.SerializedName

data class TopTracksResponse(
    @SerializedName("toptracks")
    val topTracks: ApiTopTracks
)
