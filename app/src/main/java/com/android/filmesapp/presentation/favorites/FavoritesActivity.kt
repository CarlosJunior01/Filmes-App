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
        setBottomNavigationView()
    }

    private fun setBottomNavigationView() {
        val bottomNavigationView = binding.bottomMenu
        bottomNavigationView.selectedItemId = R.id.nav_libs
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> openMoviesActivity()
                R.id.nav_search -> openSearchActivity()
                else -> openFavoriteActivity()
            }
        }
    }

    private fun openMoviesActivity(): Boolean {
        val itent = Intent(this, FilmsActivity::class.java)
        startActivity(itent)
        return true
    }

    private fun openFavoriteActivity(): Boolean {
        return true
    }

    private fun openSearchActivity(): Boolean {
        val itent = Intent(this, SearchActivity::class.java)
        startActivity(itent)
        return true
    }
}