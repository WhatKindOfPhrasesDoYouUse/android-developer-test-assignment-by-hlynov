package com.example.khlynovapp.data.model

/**
 * Модель артиста.
 */
data class Artist (
    /**
     * Псевдоним артиста.
     */
    val name: String,

    /**
     * Биография артиста.
     */
    val biography: String,

    /**
     * URL-адрес на изображения артиста.
     */
    val imageUrl: String
)