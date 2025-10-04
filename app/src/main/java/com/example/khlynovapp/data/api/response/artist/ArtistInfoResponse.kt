package com.example.khlynovapp.data.api.response.artist

import com.google.gson.annotations.SerializedName

data class ArtistInfoResponse(
    @SerializedName("artist")
    val artist: ApiArtistInfo
)
