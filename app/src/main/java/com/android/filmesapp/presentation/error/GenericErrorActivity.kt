package com.android.filmesapp.presentation.error

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.filmesapp.databinding.ActivityGenericErrorBinding
import com.android.filmesapp.presentation.movies.FilmsActivity

class GenericErrorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenericErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenericErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBackArrow.setOnClickListener {
            openMoviesActivity()
        }
    }

    private fun openMoviesActivity(){
        val intent = Intent(this, FilmsActivity::class.java)
        startActivity(intent)
    }
}