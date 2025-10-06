package com.example.khlynovapp.data.mapper

import com.example.khlynovapp.data.api.response.track.ApiTrack
import com.example.khlynovapp.data.domain.Track

object TrackMapper {
    private const val ARTIST_NO_NAME = "noname"
    private const val IMAGE_SIZE = "medium"
    private const val ZERO_RANK = 0
    fun mapToDomain(apiTrack: ApiTrack) : Track {
        return Track (
            name = apiTrack.name.replace(apiTrack.artist?.name ?: "", ""),
            artist =  apiTrack.artist?.name ?: ARTIST_NO_NAME,
            imageUrl = apiTrack.image
                .find { it.size == IMAGE_SIZE }
                ?.text.orEmpty(),
            rank = apiTrack.attr?.rank?.toIntOrNull() ?: ZERO_RANK
        )
    }
}