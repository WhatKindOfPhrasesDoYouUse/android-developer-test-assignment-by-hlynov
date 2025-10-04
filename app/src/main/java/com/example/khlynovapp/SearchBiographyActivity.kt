package com.example.khlynovapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.khlynovapp.di.ServiceLocator
import kotlinx.coroutines.launch

class SearchBiographyActivity : AppCompatActivity() {
    private val repository = ServiceLocator.musicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_bio)

        val editText = findViewById<EditText>(R.id.editText)
        val artistImage = findViewById<ImageView>(R.id.artistImage)
        val artistNameView = findViewById<TextView>(R.id.artistName)
        val artistBioView = findViewById<TextView>(R.id.artistBio)
        val searchIcon = findViewById<ImageView>(R.id.searchIcon)
        val backTextView = findViewById<TextView>(R.id.backTextView)
        val biographyButton = findViewById<Button>(R.id.biographyButton)

        backTextView.setOnClickListener {
            finish()
        }

        biographyButton.setOnClickListener {
            val artistName = editText.text.toString().trim()
            if (artistName.isNotEmpty()) {
                lifecycleScope.launch {
                    val artist = repository.searchArtist(artistName)
                    artist?.let {
                        artistNameView.text = it.name
                        artistBioView.text = it.biography

                        artistImage.load(it.imageUrl) {
                            crossfade(true)
                            placeholder(R.drawable.artist)
                        }
                    } ?: run {
                        artistNameView.text = "Артист не найден"
                        artistBioView.text = ""
                        artistImage.setImageDrawable(null);
                    }
                }
            }
        }
    }
}