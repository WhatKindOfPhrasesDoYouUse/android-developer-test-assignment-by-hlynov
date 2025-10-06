package com.example.khlynovapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.khlynovapp.data.domain.Artist
import com.example.khlynovapp.di.ServiceLocator
import kotlinx.coroutines.launch

class SearchBiographyActivity : AppCompatActivity() {
    private val repository = ServiceLocator.musicRepository

    companion object {
        private const val ARTIST_NOT_FOUND_MESSAGE = "Артист не найден"
        private const val EMPTY_ARTIST_BIO_MESSAGE = "Информация о данном артисте отсутствует"
    }

    private lateinit var editText: EditText
    private lateinit var artistImage: ImageView
    private lateinit var artistNameView: TextView
    private lateinit var artistBioView: TextView
    private lateinit var backTextView: TextView
    private lateinit var biographyButton: Button

    private fun initViews() {
        editText = findViewById<EditText>(R.id.editText)
        artistImage = findViewById<ImageView>(R.id.artistImage)
        artistNameView = findViewById<TextView>(R.id.artistName)
        artistBioView = findViewById<TextView>(R.id.artistBio)
        backTextView = findViewById<TextView>(R.id.backTextView)
        biographyButton = findViewById<Button>(R.id.biographyButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_bio)

        initViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        backTextView.setOnClickListener {
            finish()
        }

        biographyButton.setOnClickListener {
            val artistName = editText.text.toString().trim()
            if (artistName.isNotEmpty()) {
                searchArtist(artistName)
            }
        }
    }

    private fun searchArtist(artistName: String) {
        lifecycleScope.launch {
            try {
                val artist = repository.searchArtist(artistName)
                if (artist != null) {
                    displayArtistInfo(artist)
                } else {
                    showArtistNotFound()
                }
            } catch (e: Exception) {
                showError()
            }
        }
    }

    private fun displayArtistInfo(artist: Artist) {
        artistNameView.text = artist.name
        artistBioView.text = artist.biography

        loadArtistImage(artist.imageUrl)
    }

    private fun loadArtistImage(imageUrl: String) {
        artistImage.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.artist)
            error(R.drawable.artist)
        }
    }

    private fun showArtistNotFound() {
        artistNameView.text = ARTIST_NOT_FOUND_MESSAGE
        artistBioView.text = EMPTY_ARTIST_BIO_MESSAGE
        clearArtistImage()
    }

    private fun showError() {
        artistNameView.text = "Ошибка загрузки"
        artistBioView.text = "Попробуйте позже"
        clearArtistImage()
    }

    private fun clearArtistImage() {
        artistImage.setImageDrawable(null)
    }
}