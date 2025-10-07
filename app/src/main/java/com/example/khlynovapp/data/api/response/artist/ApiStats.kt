package com.example.khlynovapp.data.api.response.artist

import com.google.gson.annotations.SerializedName

data class ApiStats(
    @SerializedName("listeners")
    val listeners: String?
)
