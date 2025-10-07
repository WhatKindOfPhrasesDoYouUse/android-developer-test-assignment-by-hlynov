package com.example.khlynovapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.khlynovapp.data.api.response.ApiResult
import com.example.khlynovapp.data.api.response.error.ApiError
import com.example.khlynovapp.data.domain.Artist
import com.example.khlynovapp.di.ServiceLocator
import com.example.khlynovapp.util.AppConstants.ARTIST_NAME_INPUT
import kotlinx.coroutines.launch

class SearchBiographyActivity : AppCompatActivity() {
    private val repository = ServiceLocator.musicRepository

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
        setupScrollableBio()
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
            } else {
                showInputError()
            }
        }
    }

    private fun setupScrollableBio() {
        artistBioView.movementMethod = ScrollingMovementMethod()
    }

    private fun searchArtist(artistName: String) {
        lifecycleScope.launch {
            when (val result = repository.searchArtist(artistName)) {
                is ApiResult.Success -> {
                    if (result.data.biography.isEmpty() && result.data.imageUrl.isEmpty()) {
                        showArtistNotFound()
                    } else {
                        displayArtistInfo(result.data)
                    }
                }
                is ApiResult.Error -> {
                    showError(result.apiError.userMessage)
                    showSpecialToast(result.apiError)
                }
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
        artistBioView.text = ApiError.INVALID_PARAMETERS.userMessage
        clearArtistImage()
    }

    private fun showError(message: String) {
        artistNameView.text = "Ошибка"
        artistBioView.text = message
        clearArtistImage()
    }

    private fun showSpecialToast(error: ApiError) {
        when (error) {
            ApiError.RATE_LIMIT_EXCEEDED,
            ApiError.SERVICE_OFFLINE,
            ApiError.NETWORK_ERROR,
            ApiError.INVALID_PARAMETERS ->
                Toast.makeText(this, error.userMessage, Toast.LENGTH_LONG).show()
            else -> {}
        }
    }

    private fun showInputError() {
        Toast.makeText(this, ARTIST_NAME_INPUT, Toast.LENGTH_SHORT).show()
    }

    private fun clearArtistImage() {
        artistImage.setImageDrawable(null)
    }
}