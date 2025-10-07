package com.example.khlynovapp.data.api

import com.example.khlynovapp.data.api.response.artist.ArtistInfoResponse
import com.example.khlynovapp.data.api.response.track.TopTracksResponse
import com.example.khlynovapp.util.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApiService {
    @GET(AppConstants.ENDPOINT_BY_ARTIST_SEARCH)
    suspend fun getArtistInfo(
        @Query("artist") artistName: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json"
    ) : ArtistInfoResponse

    @GET(AppConstants.ENDPOINT_BY_TOP_TRACK_SEARCH)
    suspend fun getTopTracks(
        @Query("artist") artistName: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = 50,
        @Query("autocorrect") autocorrect: Int = 1,
        @Query("format") format: String = "json"
    ) : TopTracksResponse
}