package com.example.khlynovapp.data.mapper

import com.example.khlynovapp.data.api.response.artist.ApiArtistInfo
import com.example.khlynovapp.data.domain.Artist

object ArtistMapper {
    private const val BIO_NOT_FOUND_ERROR = "Биография отсутствует"
    private const val IMAGE_SIZE = "medium"
    private const val READ_MORE_TEXT = "Read more on Last.fm"
    private val HTML_TAGS_REGEX = Regex("<[^>]*>")
    fun mapToDomain(apiArtistInfo: ApiArtistInfo) : Artist {
        val mediumImage = apiArtistInfo.image.find {
            it.size == IMAGE_SIZE
        }

        val biography = apiArtistInfo.bio?.summary
            ?: apiArtistInfo.bio?.content
            ?: BIO_NOT_FOUND_ERROR

        val cleanBiography = biography
            .replace(HTML_TAGS_REGEX, "")
            .replace(READ_MORE_TEXT, "")
            .trim()

        return Artist(
            name = apiArtistInfo.name,
            biography = cleanBiography,
            imageUrl = mediumImage?.text ?: ""
        )
    }
}