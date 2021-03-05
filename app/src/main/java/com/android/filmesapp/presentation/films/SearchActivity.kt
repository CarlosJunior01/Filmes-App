package com.android.filmesapp.presentation.films

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFilmsBinding
import com.android.filmesapp.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bottomNavigationView = binding.bottomMenu
        bottomNavigationView.setSelectedItemId(R.id.nav_home)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> OpenFilmsActivity()
                R.id.nav_libs -> OpenFavoritActivity()
                else -> OpenSearchActivity()
            }
        }
    }

    private fun OpenFilmsActivity(): Boolean {
        val itent = Intent(this, FilmsActivity::class.java)
        startActivity(itent)
        return true
    }

    private fun OpenFavoritActivity(): Boolean {
        val itent = Intent(this, FavoritesActivity::class.java)
        startActivity(itent)
        return true
    }

    private fun OpenSearchActivity(): Boolean {
        return true
    }
}