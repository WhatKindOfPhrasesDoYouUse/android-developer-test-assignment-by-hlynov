package com.example.khlynovapp.di

import com.example.khlynovapp.data.api.ApiClient
import com.example.khlynovapp.data.api.LastFmApiService
import com.example.khlynovapp.data.repository.MusicRepository

object ServiceLocator {
    val apiService: LastFmApiService by lazy {
        ApiClient.createLastFmApiService()
    }

    val musicRepository: MusicRepository by lazy {
        MusicRepository(apiService)
    }
}