package com.android.filmesapp.presentation.player

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.lifecycle.ViewModelProviders
import com.android.filmesapp.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private val viewModel: PlayerViewModel by lazy {ViewModelProviders.of(this).get(PlayerViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        getExtras()
        screenMode()
    }

    private fun getExtras() {
        var itemSelected: Int? = null
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getInt("selectedItem")
            itemSelected = value
        }
        viewModel.getMovie(itemSelected)
    }

    private fun observeViewModel() {
        viewModel.mMoviePosition.observe(this, {
            it?.let { moviePosition ->
                val videoView = binding.videoView
                videoView.setMediaController(MediaController(this))
                videoView.setVideoURI(Uri.parse(moviePosition))
                videoView.requestFocus()
                videoView.start()
            }
        })
    }

    private fun screenMode() {
        supportActionBar!!.hide()
    }
}