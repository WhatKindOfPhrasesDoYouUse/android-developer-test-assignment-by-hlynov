package com.example.khlynovapp.data.api.response.track

import com.google.gson.annotations.SerializedName

data class ApiTopTrackAttr(
    @SerializedName("page")
    val page: String?,

    @SerializedName("totalPages")
    val totalPages: String?,

    @SerializedName("total")
    val total: String?
)
