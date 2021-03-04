package com.android.filmesapp.presentation.films

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.android.filmesapp.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenMode()

        var itemSelected: Int? = null
        val extras = intent.extras
        if (extras != null) {
            val value = extras.getInt("selectedItem")
            itemSelected = value
        }

        val video = VideoStorage()
        var videoPosition = Uri.parse(video.video_01)

        when(itemSelected){
            0 -> videoPosition = Uri.parse(video.video_01)
            1 -> videoPosition = Uri.parse(video.video_02)
            2 -> videoPosition = Uri.parse(video.video_03)
            3 -> videoPosition = Uri.parse(video.video_04)
            4 -> videoPosition = Uri.parse(video.video_05)
            5 -> videoPosition = Uri.parse(video.video_06)
            6 -> videoPosition = Uri.parse(video.video_07)
            7 -> videoPosition = Uri.parse(video.video_08)
            8 -> videoPosition = Uri.parse(video.video_09)
            9 -> videoPosition = Uri.parse(video.video_10)
            10 -> videoPosition = Uri.parse(video.video_11)
            11 -> videoPosition = Uri.parse(video.video_12)
        }

        val videoView = binding.videoView
        videoView.setMediaController(MediaController(this))
        videoView.setVideoURI(videoPosition)
        videoView.requestFocus()
        videoView.start()
    }

    private fun screenMode() {
        supportActionBar!!.hide()
    }
}