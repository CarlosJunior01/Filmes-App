package com.android.filmesapp.presentation.favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.filmesapp.R
import com.android.filmesapp.databinding.ActivityFavoritesBinding
import com.android.filmesapp.presentation.movies.FilmsActivity
import com.android.filmesapp.presentation.search.SearchActivity

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomMenu
        bottomNavigationView.setSelectedItemId(R.id.nav_libs)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> OpenFilmsActivity()
                R.id.nav_search -> OpenSearchActivity()
                else -> OpenFavoritActivity()
            }
        }
    }

    private fun OpenFilmsActivity(): Boolean {
        val itent = Intent(this, FilmsActivity::class.java)
        startActivity(itent)
        return true
    }

    private fun OpenFavoritActivity(): Boolean {
        return true
    }

    private fun OpenSearchActivity(): Boolean {
        val itent = Intent(this, SearchActivity::class.java)
        startActivity(itent)
        return true
    }
}