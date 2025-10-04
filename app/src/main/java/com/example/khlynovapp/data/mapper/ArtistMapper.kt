package com.example.khlynovapp.data.mapper

import com.example.khlynovapp.data.api.response.artist.ApiArtistInfo
import com.example.khlynovapp.data.model.Artist

/**
 * Маппер приходящего ответа от API [ApiArtistInfo] в модель типа [Artist].
 */
object ArtistMapper {
    /**
     * Сообщение если биография об артисте отсутствует.
     */
    private const val BIO_NOT_FOUND_ERROR = "Биография отсутствует"

    /**
     * Размер изображения принимаемый от API.
     */
    private const val IMAGE_SIZE = "medium"

    /**
     * Текст, который необходимо удалить из биографии.
     */
    private const val READ_MORE_TEXT = "Read more on Last.fm"

    /**
     * Регулярное выражение для удаления HTML-тегов из текста биографии.
     */
    private val HTML_TAGS_REGEX = Regex("<[^>]*>")

    /**
     * Преобразует ответ от API [apiArtistInfo] в модель типа [Artist]
     * с удалением HTML-тегов и информации от LastFM.
     */
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