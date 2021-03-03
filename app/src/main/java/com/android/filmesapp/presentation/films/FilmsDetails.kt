package com.android.filmesapp.presentation.films

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFilmsDetailsBinding
import com.android.filmesapp.presentation.authentication.FormLoginActivity

class FilmsDetails : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBackArrow.setOnClickListener {
            OpenFilmsActivity()
        }

        binding.imgStar.setOnClickListener {
            binding.imgStar.setImageResource(R.drawable.ic_star)
        }
    }

    private fun OpenFilmsActivity() {
        var intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }

}