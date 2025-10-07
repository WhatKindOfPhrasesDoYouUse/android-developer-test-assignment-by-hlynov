package com.example.khlynovapp.data.repository

import com.example.khlynovapp.BuildConfig
import com.example.khlynovapp.data.api.LastFmApiService
import com.example.khlynovapp.data.api.response.ApiResult
import com.example.khlynovapp.data.api.response.error.ApiError
import com.example.khlynovapp.data.mapper.ArtistMapper
import com.example.khlynovapp.data.domain.Artist
import com.example.khlynovapp.data.domain.Track
import com.example.khlynovapp.data.mapper.TrackMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import kotlin.random.Random

class MusicRepository(
    private val apiService: LastFmApiService
) {
    suspend fun searchArtist(artistName: String): ApiResult<Artist> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getArtistInfo(
                artistName = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                format = "json"
            )
            ApiResult.Success(ArtistMapper.mapToDomain(response.artist))
        } catch (e: HttpException) {
            ApiResult.Error(handleHttpException(e))
        } catch (e: IOException) {
            ApiResult.Error(ApiError.NETWORK_ERROR)
        } catch (e: Exception) {
            ApiResult.Error(ApiError.UNKNOWN_ERROR)
        }
    }

    suspend fun getRandomTopTracks(artistName: String): ApiResult<List<Track>> = withContext(Dispatchers.IO) {
        try {
            val firstResponse = apiService.getTopTracks(
                artistName = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                page = 1
            )

            val totalPages = firstResponse.topTracks.attr.totalPages?.toIntOrNull() ?: 1
            val randomPage = if (totalPages > 1) Random.nextInt(1, totalPages) else 1

            val response = apiService.getTopTracks(
                artistName = artistName,
                apiKey = BuildConfig.LASTFM_API_KEY,
                page = randomPage
            )

            val tracks = response.topTracks.track
                .filter { it.image.any { img -> img.text.isNotEmpty() } }
                .shuffled()
                .take(3)
                .map { TrackMapper.mapToDomain(it) }

            ApiResult.Success(tracks)
        } catch (e: HttpException) {
            ApiResult.Error(handleHttpException(e))
        } catch (e: IOException) {
            ApiResult.Error(ApiError.NETWORK_ERROR)
        } catch (e: Exception) {
            ApiResult.Error(ApiError.UNKNOWN_ERROR)
        }
    }

    private fun handleHttpException(e: HttpException): ApiError {
        return when (e.code()) {
            2 -> ApiError.INVALID_SERVICE
            3 -> ApiError.INVALID_METHOD
            4 -> ApiError.AUTHENTICATION_FAILED
            5 -> ApiError.INVALID_FORMAT
            6 -> ApiError.INVALID_PARAMETERS
            7 -> ApiError.INVALID_RESOURCE
            8 -> ApiError.OPERATION_FAILED
            9 -> ApiError.INVALID_SESSION_KEY
            10 -> ApiError.INVALID_API_KEY
            11 -> ApiError.SERVICE_OFFLINE
            13 -> ApiError.INVALID_METHOD_SIGNATURE
            16 -> ApiError.TEMPORARY_ERROR
            26 -> ApiError.SUSPENDED_API_KEY
            29 -> ApiError.RATE_LIMIT_EXCEEDED
            else -> ApiError.UNKNOWN_ERROR
        }
    }
}