package com.example.khlynovapp.data.repository

import com.example.khlynovapp.BuildConfig
import com.example.khlynovapp.data.api.LastFmApiService
import com.example.khlynovapp.data.model.Artist
import com.example.khlynovapp.util.AppConstants

class MusicRepository(
    private val apiService: LastFmApiService
) {
    suspend fun searchArtist(artistName: String) : Artist? {
        return try {
            var response = apiService.getArtistInfo(
                artistName = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                format = "json"
            )
            response.artist.toDomainArtist()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
