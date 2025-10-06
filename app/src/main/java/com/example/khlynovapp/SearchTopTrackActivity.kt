package com.example.khlynovapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.khlynovapp.data.domain.Track
import com.example.khlynovapp.di.ServiceLocator
import kotlinx.coroutines.launch

class SearchTopTrackActivity : AppCompatActivity() {
    private val repository = ServiceLocator.musicRepository

    companion object {
        private const val ARTIST_NAME_INPUT = "Введите имя артиста"
        private const val TRACKS_NOT_FOUND = "Треки не найдены"
        private const val TRACKS_LOAD_ERROR = "Ошибка при загрузке треков"
    }

    private lateinit var editText: EditText
    private lateinit var searchButton: Button
    private lateinit var tracksContainer: LinearLayout
    private lateinit var backText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_top_track)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        editText = findViewById(R.id.editText)
        searchButton = findViewById(R.id.biographyButton)
        tracksContainer = findViewById(R.id.tracksContainer)
        backText = findViewById(R.id.backTextView)
    }

    private fun setupClickListeners() {
        setupSearchButton()
        setupBackButton()
    }

    private fun setupSearchButton() {
        searchButton.setOnClickListener {
            val artistName = getArtistNameFromInput()
            if (isArtistNameValid(artistName)) {
                searchTracks(artistName)
            } else {
                showInputError()
            }
        }
    }

    private fun setupBackButton() {
        backText.setOnClickListener {
            finish()
        }
    }

    private fun getArtistNameFromInput(): String {
        return editText.text.toString().trim()
    }

    private fun isArtistNameValid(artistName: String): Boolean {
        return artistName.isNotEmpty()
    }

    private fun showInputError() {
        Toast.makeText(this, ARTIST_NAME_INPUT, Toast.LENGTH_SHORT).show()
    }

    private fun searchTracks(artistName: String) {
        clearPreviousResults()

        lifecycleScope.launch {
            try {
                val tracks = repository.getRandomTopTracks(artistName)
                handleTracksResult(tracks)
            } catch (e: Exception) {
                handleTracksError(e)
            }
        }
    }

    private fun clearPreviousResults() {
        tracksContainer.removeAllViews()
    }

    private fun handleTracksResult(tracks: List<Track>) {
        if (tracks.isEmpty()) {
            showEmptyState()
        } else {
            displayTracks(tracks)
        }
    }

    private fun handleTracksError(exception: Exception) {
        exception.printStackTrace()
        showErrorState()
    }

    private fun showEmptyState() {
        showMessage(TRACKS_NOT_FOUND)
    }

    private fun showErrorState() {
        showMessage(TRACKS_LOAD_ERROR)
    }

    private fun displayTracks(tracks: List<Track>) {
        tracks.forEach { track ->
            val trackView = createTrackView(track)
            tracksContainer.addView(trackView)
        }
    }

    private fun createTrackView(track: Track): View {
        val trackView = layoutInflater.inflate(R.layout.simple_track_item, tracksContainer, false)

        bindTrackData(trackView, track)
        loadTrackImage(trackView, track.imageUrl)

        return trackView
    }

    private fun bindTrackData(trackView: View, track: Track) {
        val trackNameTextView = trackView.findViewById<TextView>(R.id.trackNameTextView)
        val trackRank = trackView.findViewById<TextView>(R.id.rankTextView)
        val trackArtistName = trackView.findViewById<TextView>(R.id.artistTextView)

        trackNameTextView.text = track.name
        trackRank.text = "Ранг: ${track.rank}"
        trackArtistName.text = track.artist
    }

    private fun loadTrackImage(trackView: View, imageUrl: String) {
        val trackImageView = trackView.findViewById<ImageView>(R.id.trackImage)
        trackImageView.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.artist)
            error(R.drawable.artist)
        }
    }

    private fun showMessage(text: String) {
        val message = TextView(this).apply {
            this.text = text
            textSize = 16f
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setTextColor(resources.getColor(android.R.color.darker_gray, null))
        }
        tracksContainer.addView(message)
    }
}