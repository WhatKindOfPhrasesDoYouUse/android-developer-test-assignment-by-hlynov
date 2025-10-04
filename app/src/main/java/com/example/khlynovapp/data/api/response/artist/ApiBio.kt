package com.example.khlynovapp.data.api.response.artist

import com.google.gson.annotations.SerializedName

data class ApiBio(
    @SerializedName("content")
    val content: String?,

    @SerializedName("summary")
    val summary: String?
)
