package com.example.khlynovapp.data.repository

import com.example.khlynovapp.BuildConfig
import com.example.khlynovapp.data.api.LastFmApiService
import com.example.khlynovapp.data.mapper.ArtistMapper
import com.example.khlynovapp.data.domain.Artist
import com.example.khlynovapp.data.domain.Track
import com.example.khlynovapp.data.mapper.TrackMapper
import kotlin.random.Random

class MusicRepository(
    private val apiService: LastFmApiService
) {
    suspend fun searchArtist(artistName: String) : Artist? {
        val response = apiService.getArtistInfo(
            artistName = artistName,
            apiKey = BuildConfig.LASTFM_API_KEY,
            format = "json"
        )

        return ArtistMapper.mapToDomain(response.artist)
    }

    suspend fun getRandomTopTracks(artistName: String) : List<Track> {
        val firstResponse = apiService.getTopTracks(
            artistName = artistName,
            apiKey = BuildConfig.LASTFM_API_KEY,
            page = 1
        );

        val totalPages = firstResponse.topTracks.attr.totalPages?.toIntOrNull() ?: 1

        val randomPage = if (totalPages > 1) Random.nextInt(1, totalPages) else 1

        val response = apiService.getTopTracks(
            artistName = artistName,
            apiKey = BuildConfig.LASTFM_API_KEY,
            page = randomPage
        )

        return response.topTracks.track
            .filter {
                it.image.any {
                    img -> img.text.isNotEmpty()
                }
            }
            .shuffled()
            .take(3)
            .map {
                TrackMapper.mapToDomain(it)
            }
    }
}
