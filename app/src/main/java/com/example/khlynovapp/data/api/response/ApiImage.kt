package com.example.khlynovapp.data.api.response

import com.google.gson.annotations.SerializedName

data class ApiImage(
    @SerializedName("#text")
    val text: String,

    @SerializedName("size")
    val size: String
)
