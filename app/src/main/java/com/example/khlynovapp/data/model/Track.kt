package com.example.khlynovapp.data.model

import com.example.khlynovapp.util.AppConstants

data class Track (
    val name: String,
    val artist: String,
    val imageUrl: String
) {
    fun getSafeImageUrl(): String {
        return imageUrl.ifBlank {
            AppConstants.DEFAULT_TRACK_IMAGE_URL
        }
    }
}
