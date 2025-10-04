package com.example.khlynovapp.data.repository

import com.example.khlynovapp.BuildConfig
import com.example.khlynovapp.data.api.LastFmApiService
import com.example.khlynovapp.data.mapper.ArtistMapper
import com.example.khlynovapp.data.model.Artist

/**
 * Репозиторий для работы с данными предоставляемыми API.
 */
class MusicRepository(

    /**
     * Интерфейс для работы с API.
     */
    private val apiService: LastFmApiService
) {
    /**
     * Выполняет поиск информации об артисте по имени [artistName]
     * и возвращает объект типа [Artist].
     */
    suspend fun searchArtist(artistName: String) : Artist? {
        return try {
            val response = apiService.getArtistInfo(
                artistName = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                format = "json"
            )
            ArtistMapper.mapToDomain(response.artist)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
