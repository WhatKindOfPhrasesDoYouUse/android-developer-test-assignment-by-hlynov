package com.example.khlynovapp.data.api

import com.example.khlynovapp.data.api.response.artist.ArtistInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApiService {
    @GET("?method=artist.getInfo")
    suspend fun getArtistInfo(
        @Query("artist") artistName: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ) : ArtistInfoResponse
}